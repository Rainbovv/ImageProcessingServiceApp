package main;

import behavior.DirectoryObserver;


public class Application {

	public static void main(String[] args) {

		DirectoryObserver directoryObserver = new DirectoryObserver();


		directoryObserver.observe("jpeg");
	}
}
