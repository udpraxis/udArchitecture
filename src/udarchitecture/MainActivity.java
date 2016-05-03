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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Darwin Subramaniam
 */
public class MainActivity {
    
    public static void main(String[] args) {
        
        
        /**
         * This BlockingQueue will be the storage point 
         * for all the data coming from the server and 
         * to be taken by the appropriate Threads.
         */
        BlockingQueue speedQueue = new ArrayBlockingQueue(1024);
        
        BlockingQueue steeringQueue = new ArrayBlockingQueue(1024);
        /*************************************************************/
        
        /**
         * Creating new Instances of Lengkung and Beschleunigung
         */
        LengkungActor lengkung = new LengkungActor();
        BeschleunigungActor beschleunigung = new BeschleunigungActor();
        
        /**
         * Making the Lengkung and Beschleunigun as a Thread properties
         */
        Thread l = new Thread(lengkung);
        Thread b = new Thread(beschleunigung);
        
        
        
        
        
        
    }
}
