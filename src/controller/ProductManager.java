package controller;

import model.Product;
import storage.ReadWrite;
import storage.ReadWriteFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class ProductManager implements Serializable {
    private ReadWrite readWriteData = ReadWriteFile.getInstance();
    public ArrayList<Product> productList = new ArrayList<Product>();


    public void addProducts(Product product) {
        productList.add(product);
    }

    public void display() {
        for (Product x : productList
        ) {
            System.out.println(x.toString());
        }
    }

    public void displayProductFile() {
        int count=1;
        try{
            ArrayList<Product> displayFile = readWriteData.readData("product.csv");
            for (Product i: displayFile
            ) {
                System.out.println(count +". "+ i);
                count++;
            }
        } catch (Exception e) {
            System.err.println("Gian hàng trống!!!!");
            e.getMessage();
        }
    }

    public void editProduct(int id, Product product) {
        productList.set(id,product);
    }

    public void deleteProduct(int product) {
        productList.remove(product);
    }

    public void deleteAll() {
        productList.removeAll(productList);
    }

    public int checkID(String id){
        int check = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (id.equals(productList.get(i).getId())){
                check = i;
            }
        }
        return check;
    }

    public void sortLowestFirst(){
        productList.sort(Comparator.comparingDouble(Product::getPrice));
    }

    public void writeFile() {

        readWriteData.writeData(productList, "product.csv");
        System.out.println("Ghi file thành công");
    }

    public void readFile() {
        productList = readWriteData.readData("product.csv");
    }
}
