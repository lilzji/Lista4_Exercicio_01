package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCavaleiros;

public class Main {

	public static void main(String[] args) {
		String pedra = "free";
		String tocha = "free";
		int permissao = 1;
		Semaphore mutex = new Semaphore(permissao);
		for (int id = 0; id < 4; id++) {
			Thread threadCavaleiros = new ThreadCavaleiros(pedra, tocha, id + 1, mutex);
			threadCavaleiros.start();
		}
	}

}
