package Starter;

import java.awt.EventQueue;

import businessLogic.LoginWindowManager;
import presentationLayer.LoginWindow;

/**
 * @author sledzik_23
 * @since 2016-01-24
 */

public class Starter {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginWindowManager(new LoginWindow());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
