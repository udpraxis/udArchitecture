/*
 * The MIT License
 *
 * Copyright 2016 darwin.
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Darwin Subramanian
 * Using DataColletor will Save all the Data in CSV file and according to the 
 * proper header.
 * 
 * Example
 * ID       TimeAction
 * 1        19/04/16 16:11:11
 */


public class DataCollector implements Runnable{
    
    String fileName ;
    
    DataCollector(String fileName){
        setfileName(fileName);
    }
    
    public void accumulateData(String ID,long time){
       
    }
    
    private void setfileName(String filename){
        this.fileName = filename + getDate();         
    }
    
    
    /**
     * Giving the Date in String Format
     * @return String currentDate  
     */
    private String getDate(){
        String currentDate;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        currentDate = dateFormat.format(date);
        return currentDate;
    }

    @Override
    public void run() {
        
    }
    
    
}