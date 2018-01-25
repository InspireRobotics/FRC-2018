package io.github.inspirerobotics.leddar;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Parity;

public class Leddar{

	private static final Parity PARITY = Parity.kNone;
	private static final int STOP_BITS = 1;
	
	private SerialPort serialPort;
	
	public Leddar(SerialPort.Port port, int baudRate) {
		serialPort = new SerialPort(baudRate, port, STOP_BITS, PARITY);
	}
	
	public void getDetections() {
		byte[] buffer = new byte[7];
	}
	
}
