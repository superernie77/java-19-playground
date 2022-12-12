package se77;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

import javax.management.RuntimeErrorException;

public class VirtualThreadExample {

	// This is how the old Threads are started now. Class Thread has a static method Thread.ofPlatform()
	public static void startPlatformThread() throws InterruptedException {

		// create a platform thread
		Thread pthread = Thread.ofPlatform().unstarted(() -> System.out.println(Thread.currentThread()));

		// ... start it
		pthread.start();

		// and wait till its done
		pthread.join();

		// this will print the object reference of a regular thread in the console
	}

	// Example for the new virtual Threads
	public static void startVirtualThreads() throws InterruptedException {

		Instant begin = Instant.now();

		// start a lot of virtual threads (1 million). With platform Threads this would already crash the machine
		var threads = IntStream.range(0, 1_000_000).mapToObj(i -> Thread.ofVirtual().unstarted(() -> {
			
			// only for the first one. we don't want 1 million lines printed
			if (i == 0) {
				// virtual thread sits on a worker thread. Print the reference to it.
				System.out.println(Thread.currentThread());
			}
			
			// suspend thread
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			
			// when restarted again only for the first one. 
			if (i == 0) {
				// virtual thread sits on a different worker thread now
				// so it changed threads while sleeping.
				// this makes sure worker threads don't block while virtual thread sleeps
				System.out.println(Thread.currentThread());
			}
		})).toList();

		// start all threads
		threads.forEach(Thread::start);

		// ... and wait for them to finish
		for (Thread thread : threads) {
			thread.join();
		}

		// calculate some statistics
		Instant end = Instant.now();
		// should take only a couple of seconds to start and finish 1 million threads
		System.out.println(Duration.between(begin, end).toMillis() + "ms");
		System.out.println("# cores = " + Runtime.getRuntime().availableProcessors());
		
		// if you change line 30 from ofVirtual to ofPlatform, the test never finishes because 1 million platform threads block the entire system

	}

}
