public class Reservation {
    private int resNumber;
    private String resCustName;
    private boolean resFreqFlyer;

    //parameterized constructor
    Reservation(int resNum, String resName, boolean freqFlyer){
        setResNumber(resNum);
        setResCustName(resName);
        setResFreqFlyer(freqFlyer);

    }

    //setters
    public void setResNumber(int num){
        resNumber = num;
    }
    public void setResCustName(String name){
        resCustName = name;
    }
    public void setResFreqFlyer(boolean flyer){
        resFreqFlyer = flyer;
    }

    //getters
    public int getResNumber(){
        return resNumber;
    }
    public String getResCustName(){
        return resCustName;
    }
    public boolean getResFreqFlyer(){
        return resFreqFlyer;
    }

}
