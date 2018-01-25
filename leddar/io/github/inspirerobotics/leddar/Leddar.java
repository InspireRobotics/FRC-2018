package io.github.inspirerobotics.leddar;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Parity;

public class Leddar{

	private static final Parity PARITY = Parity.kNone;
	private static final int STOP_BITS = 1;
	
	/*
	 * Error Codes
	 */
	private static final int ERR_LEDDAR_BAD_FUNCTION_CODE = 0;
	private static final int ERR_LEDDAR_BAD_CRC = 1;
	
	/**
	 * The serial port for the LEDDAR sensor
	 */
	private SerialPort serialPort;
	
	/**
	 * The address of the LEDDAR sensor
	 */
	private byte slaveAddress = 0x01;
	
	
	public Leddar(SerialPort.Port port, int baudRate) {
		serialPort = new SerialPort(baudRate, port, STOP_BITS, PARITY);
	}
	
	public void update() {
		if(serialPort.getBytesReceived() > 0)
			handleIncomingPacket();
	}
	
	private void handleIncomingPacket() {
		//Read incoming bytes
		byte[] buffer = serialPort.read(25);
		
		//Check CRC
		if(!CRC16.checkCRC16(buffer)) {
			DriverStation.reportError("LEDDAR Error: " + ERR_LEDDAR_BAD_CRC, false);
			return;
		}
		
		//Check the function code
		if(buffer[0] != 0x04) {
			DriverStation.reportError("LEDDAR Error: " + ERR_LEDDAR_BAD_FUNCTION_CODE, false);
			return;
		}
		
		//Check the number of registers return
		
	}

	/**
	 * Sends a request to get info from the LEDDAR sensor
	 */
	public void sendRequest() {
		byte[] buffer = new byte[7];
		
		//Setup bytes for the message
		buffer[0] = slaveAddress;
		buffer[1] = 0x04;//Function Code; in this case 0x04 is the read command
		buffer[2] = 0;
		buffer[3] = 20;//Starting address of the register
		buffer[4] = 0;
		buffer[5] = 10;//Number of registers to read
	
		//Add CRC bytes
		CRC16.addCRC16(buffer);
		
		serialPort.write(buffer, buffer.length);
	}
	
	/**
	 * The address of the LEDDAR sensor
	 */
	public byte getSlaveAddress() {
		return slaveAddress;
	}
	
	/**
	 * The address of the LEDDAR sensor
	 */
	public void setSlaveAddress(byte slaveAddress) {
		this.slaveAddress = slaveAddress;
	}
	
}
