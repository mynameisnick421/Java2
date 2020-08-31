public class Books {
    private String title;
    private String author;
    private double price;

    Books(){
        title = "Default Title";
        author = "Default Author";
        price = 0;
    }
    Books(String t, String a, double p){
        setTitle(t);
        setAuthor(a);
        setPrice(p);
    }

    public void setPrice(double p) { price = p;}
    public void setAuthor(String a) { author = a; }
    public void setTitle(String t) { title = t; }

    public String getTitle(){ return title; }
    public String getAuthor(){ return author; }
    public double getPrice(){ return price; }

    public String displayBook(){
        return "Title: "+ getTitle()+ "\n"+
                "Author: "+getAuthor() +"\n"+
                "Price: "+getPrice();
    }
}

