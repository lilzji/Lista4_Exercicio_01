package controller;

import java.util.concurrent.Semaphore;

public class ThreadCavaleiros extends Thread {

	static String tocha;
	static String pedra;
	private int distanciaMax = 100;
	private int id;
	static int distPorta = (int) ((Math.random() * 9) + 2);
	Semaphore mutex;
	static int porta[] = new int[4];

	public ThreadCavaleiros(String pedra, String tocha, int id, Semaphore mutex) {
		this.id = id;
		this.pedra = pedra;
		this.tocha = tocha;
		this.mutex = mutex;
	}

	public void run() {
		corredor();
	}

	private void corredor() {
		int distanciaPercorrida = 0;
		while (distanciaMax > 0) {
			if (distanciaPercorrida >= 10) {
				if (tocha.contains("free")) {
					tocha = "taken";
					distanciaPercorrida += (int) ((Math.random() * 3) + 2) + 2;
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Cavaleiro: " + id + " pegou a tocha");
				}
			}
			if (distanciaPercorrida >= 50) {
				if (pedra.contains("free")) {
					pedra = "taken";
					distanciaPercorrida += (int) ((Math.random() * 3) + 2);
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Cavaleiro: " + id + " pegou a pedra");
				}
			}
			distanciaPercorrida += (int) ((Math.random() * 3) + 2);
			distanciaMax -= distanciaPercorrida;
		}
		porta()
	}

	private void porta() {
		int distanciaPercorrida = 0;
		while (distPorta > 0) {
			distanciaPercorrida += (int) ((Math.random() * 3) + 2);
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			distPorta -= distanciaPercorrida;
		}
		// SECAO CRITICA
		try {
			mutex.acquire();
			vidaOuMorte();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutex.release();
			// FIM DA SECAO CRITICA
		}

	}

	private void vidaOuMorte() {
		
	}

}
