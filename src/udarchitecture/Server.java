/*
 * The MIT License
 *
 * Copyright 2016 Darwin Subramaniam.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package udarchitecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darwin Subramaniam
 */
public class Server {

    //setting the ServerSocket as t
    private ServerSocket serverSocket;
    private Socket clientSocket;
    /**
     * Difference between isConnected and initialised is 
     * isConnected only occur to be used 
     * when the server is validated.
     * initialised is true when the initialisation 
     * if constructor is done.
     * initialised is false when there is not 
     * initialisation of the server is done.
     */
    private boolean isConnected = false;
    private boolean initialised = false;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String oldinput = "";

    Server(int portNumber) {

        if (!isConnected()) {
            if (portNumberManager(portNumber)) {

                try {
                    serverSocket = new ServerSocket(portNumber);
                    try {
                        System.out.println("Waiting For Client in " +
                                getIPAddress());

                        clientSocket = serverSocket.accept();
                        
                        //Contructor is activated
                        initialised  = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Denied connection "
                                + "from a client");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Error in Port number");

            }
        }
    }

    /**
     * NetWorkInterface is used here so 
     * it gets a Enumeration 
     * name number
     * example 
     * eth0 fe80:0:0:0:5d40:9c60:d4a5:8712%eth0 
     * wlan0 192.168.1.105
     *
     * @return
     */
    public String getIPAddress() {

        String IPAddress = null;
        try {
            Enumeration<NetworkInterface> nics = NetworkInterface
                    .getNetworkInterfaces();
            while (nics.hasMoreElements()) {
                NetworkInterface nic = nics.nextElement();
                Enumeration<InetAddress> addrs = nic.getInetAddresses();
                while (addrs.hasMoreElements()) {
                    InetAddress addr = addrs.nextElement();
                    if (nic.getName().contains("wlan")) {
                        IPAddress = addr.getHostAddress();
                    }

                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return IPAddress;
    }

    /**
     * This check if the constructor is giving a valid port number
     *
     * @param port
     * @return
     */
    private boolean portNumberManager(int port) {
        boolean succes;

        //Check if the interger send is consist of 4 digit number
        if (port >= 1000 && port <= 9999) {
            succes = true;
        } else {
            System.out.println();
            System.out.println("Port Number should be consist of 4 digit number only");
            System.out.println();
            succes = false;
        }

        return succes;
    }

    /**
     * Data Send by client to server is Read here.
     *
     * @return String is return from the BufferReader.
     */
    public String readClient() {
        String dataReadable = " ";
        if (!serverSocket.isClosed()) {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));// in is a java.io.BufferReader Type
                dataReadable = in.readLine();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("BufferedReader getting error");
            }
        } else {
            System.out.println("Server socket is closed");
        }
        return dataReadable;
    }

    /**
     * Data is Sent to client from server
     *
     * @param data Use String type .
     */
    public void writeClient(String data) {
        if (!serverSocket.isClosed()) {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println(data);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(" PrintWriter giving error");
            }
        } else {
            System.out.println("Server socket is closed");
        }
    }

    /**
     * checks if any client is connected to the server
     * and return false if there is problem while Constructing.
     *
     * @return
     */
    public boolean isConnected() {
        if(initialised){
            isConnected = clientSocket.isConnected();
        }else{
            isConnected = false;
        }
        
        return isConnected;
    }

    /**
     * check if newData arrives not really needed
     *
     * @return
     */
    public boolean newData() {
        boolean newdata;
        String readClient = readClient();
        if (!readClient.equals(oldinput)) {
            newdata = true;
        } else {
            newdata = false;
        }

        return newdata;

    }

}
