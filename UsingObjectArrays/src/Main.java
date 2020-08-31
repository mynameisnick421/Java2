public class Main {

    public static void main(String[] args) {
	Books[] bookArry = new Books[3];
	bookArry[0] = new Books();
	bookArry[1] = new Books("The catcher in the rye","Salinger",7.99);
	bookArry[2] = new Books("Java Programming","Farrell",125.00);

	for(int i = 0; i< bookArry.length && bookArry[i] != null ;i++){
	    System.out.println(bookArry[i].displayBook() +"\n");
    }
        for (Books b:bookArry) {
            if(b != null){
                System.out.println(b.displayBook() +"\n");
            }
        }
    }
}
