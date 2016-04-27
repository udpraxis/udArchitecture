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

/**
 *
 * @author Darwin Subramaniam
 */
public class DataAnalyse implements Runnable{
    
    //alle Notwediger Parameter zu initializierung
    private final String dataspalt; //wo wird die daten splaten
    private final String idSpalt; // wo wird die daten enden
    private final int idPosition ;
    private final int commandPosition;
    private final int speedPosition;
    private final int steeringPosition;
    private final String finalDataConnector;
    private final int finalDataArrayBlock;  // in a case of error the data CurrentdataArray will hv less or more array
                                      // than expected .. this will be a checking if the data recieved is a
                                      // clean data or a corrupted on. arrays are counted from 0
    private final boolean isSplitEqualConnector;

    private String id = " ";
    private String data;
    private String[] currentArrayData;

    //DataAnalyse's Constructot

    //einfach Konstruktor
    public DataAnalyse(String dataspalt,String idSpalt,int idPosition,
            int commandPosition,int speedPosition,int steeringPosition,
            boolean isSplitEqualConnector, int finalDataArrayBlock){
        this.dataspalt = dataspalt;
        this.idSpalt = idSpalt;
        this.idPosition = idPosition;
        this.commandPosition = commandPosition;
        this.speedPosition = speedPosition;
        this.steeringPosition = steeringPosition;
        this.finalDataConnector = dataspalt;
        this.isSplitEqualConnector = isSplitEqualConnector;
        this.finalDataArrayBlock = finalDataArrayBlock;

    }

    public DataAnalyse(String dataspalt,String idSpalt,int idPosition,
            int commandPosition,int speedPosition,int steeringPosition,
            String finalDataConnector, int finalDataArrayBlock){
        this.dataspalt = dataspalt;
        this.idSpalt = idSpalt;
        this.idPosition = idPosition;
        this.speedPosition = speedPosition;
        this.steeringPosition = steeringPosition;
        this.finalDataConnector = finalDataConnector;
        this.isSplitEqualConnector = false;
        this.finalDataArrayBlock = finalDataArrayBlock;
        this.commandPosition = commandPosition;

    }

    public void setData(String data){
        this.data = data;
        runAnalyseData();
    }

    private void runAnalyseData(){
        currentArrayData = dataTrennen();
        idTrennen();
        

    }

    //Trennen die Daten in Array und Array erste position ist 0
    private String[] dataTrennen(){
        String[] arraydata = data.split(dataspalt);
        return arraydata;
    }

    // Trennen die IDposition um die ID nummer zu kriegen
    private void idTrennen(){
        String IDLocation = currentArrayData[idPosition];
        String[] temp_array = IDLocation.split(idSpalt);
        id = temp_array[1];
    }

    public String getID(){
        return id;
    }
    
    public String getCommand(){
        String command = currentArrayData[commandPosition];
        return command;
    }
    
    
    /**
     * checks if the data received and the expected data length received is same
     * if data is more or less then then expected length of array data is corrupted
     * @return 
     */
    public boolean isDataValid(){
        boolean valid = false;
        if(currentArrayData.length == finalDataArrayBlock){
            valid = true;
        }
        return valid;
    }



    //Return String Form of the Full data Recieved
    public String getCompleteData(){

        int length = currentArrayData.length;
        String temp = " ";
        if (length == finalDataArrayBlock){
            if(isSplitEqualConnector){
                temp =  data;
                //System.out.println(temp);
            }else{
                /***this part is not complete .. The output data is not how
                 * i planned... Research on this more
                 */
                for(int x = 0 ; x<= length-1; x++){
                    if(x== length-1){
                        //The last data Added
                        temp = temp + currentArrayData[x];

                    }else{
                        //The still havent reach the last data
                        temp = temp + currentArrayData[x];
                        temp = temp + (finalDataConnector);
                    }
                }
            }

        }else {
            temp ="ID:e:ER:00:00@";
            System.out.println("Data beschädigt");
        }
        return temp;
    }
    
    /**
     * 
     * @return String of speed
     */
    public int getSpeedInt(){
         String temp = currentArrayData[speedPosition];
         int speed = Integer.parseInt(temp);
         return speed;
    }
    
    public int getSteeringInt(){
        String temp = currentArrayData[steeringPosition];
        int steering = Integer.parseInt(temp);
        return steering;
    }
    
    public double getSpeedDouble(){
         String temp = currentArrayData[speedPosition];
         Double speed = Double.parseDouble(temp);
         return speed;
    }
    
    public double getSteeringDouble(){
        String temp = currentArrayData[steeringPosition];
        double steering = Double.parseDouble(temp);
        return steering;
    }
    
    public void inAction(){
        // take the setData and properlly set all the things in order 
        // making the automation of Data split as easy as possible
    }

    @Override
    public void run()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
