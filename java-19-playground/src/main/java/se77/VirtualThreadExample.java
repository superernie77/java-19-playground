package se77;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

import javax.management.RuntimeErrorException;

public class VirtualThreadExample {

	public static void startPlatformThread() throws InterruptedException {

		// create a new platform thread
		Thread pthread = Thread.ofPlatform().unstarted(() -> System.out.println(Thread.currentThread()));

		// ... start it
		pthread.start();

		// and wait till its done
		pthread.join();

		// this will print the object reference of a regular thread in the console
	}

	public static void startVirtualThreads() throws InterruptedException {

		Instant begin = Instant.now();

		// start virtual threads
		var threads = IntStream.range(0, 1_000_000).mapToObj(i -> Thread.ofVirtual().unstarted(() -> {
			if (i == 0) {
				// virtual thread sits on a worker thread
				System.out.println(Thread.currentThread());
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if (i == 0) {
				// virtual thread sits on a different worker thread now
				// so it changed threads while sleeping.
				// this makes sure worker threads don't block while virtual thread sleeps
				System.out.println(Thread.currentThread());
			}
		})).toList();

		threads.forEach(Thread::start);

		for (Thread thread : threads) {
			thread.join();
		}

		Instant end = Instant.now();
		// should take only a couple of seconds to start 1 million threads
		System.out.println(Duration.between(begin, end).toMillis() + "ms");
		System.out.println("# cores = " + Runtime.getRuntime().availableProcessors());
		
		// if you change line 30 to platform threads, the test never finishes because 1 million platform threads block the entire system

	}

}
