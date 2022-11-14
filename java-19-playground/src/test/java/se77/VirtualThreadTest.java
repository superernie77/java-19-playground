package se77;

import static se77.VirtualThreadExample.*;

import org.junit.jupiter.api.Test;

public class VirtualThreadTest {

	
	@Test
	public void startPlatformThreadTest() throws InterruptedException {
		startPlatformThread();
	}
	
	@Test
	public void startVirtualThreadTest() throws InterruptedException {
		startVirtualThreads();
	}
}
