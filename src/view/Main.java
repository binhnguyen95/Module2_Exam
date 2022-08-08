package view;

import controller.ProductManager;
import model.Product;
import storage.ReadWrite;
import storage.ReadWriteFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main implements Serializable {
    static ProductManager productManager = new ProductManager();
    static ReadWrite readWriteData = ReadWriteFile.getInstance();

    public static void main(String[] args) {
        ArrayList<Product> arrayList = new ArrayList<>();

        Product product1 = new Product("15","Thịt chó", 5.5, 10, "chó nướng");
        Product product2 = new Product("09","Thịt mèo", 7.4, 5, "mèo nướng");
        Product product3 = new Product("11","Thịt lợn", 2.5, 10, "lợn nướng");
        productManager.addProducts(product1);
        productManager.addProducts(product2);
        productManager.addProducts(product3);

        while (true) {
            System.out.println("\n-------------CỬA HÀNG THỜI TRANG-------------");
            System.out.println("Chọn chức năng theo số (để tiếp tục)");
            System.out.println("[1] Hiển thị danh sách hiện có");
            System.out.println("[2] Thêm sản phẩm");
            System.out.println("[3] Sửa sản phẩm");
            System.out.println("[4] Xóa sản phẩm");
            System.out.println("[5] Hiển thị theo giá từ thấp lên cao");
            System.out.println("[6] Đọc file và hiển thị file");
            System.out.println("[7] Ghi file ");
            System.out.println("[0] THOÁT");
            System.out.println("----------------------------------------------");
            System.out.print("[Chọn]:\t");
            try {
                Scanner scan = new Scanner(System.in);
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        productManager.display();
                        break;
                    case 2:
                        addProduct();
                        break;
                    case 3:
                        editProduct();
                        break;
                    case 4:
                        removeProduct();
                        break;
                    case 5:
                        productManager.sortLowestFirst();
                        productManager.display();
                        break;
                    case 6:
                        productManager.readFile();
                        productManager.displayProductFile();
                        break;
                    case 7:
                        readWriteData.writeData(productManager.productList, "product.csv");
                        break;
                    case 0:
                        System.exit(0);
                        System.err.println("SYSTEM SHUTDOWN");
                        return;
                    default:
                        System.err.println("Lựa chọn không tồn tại!!!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Sai định dạng vui lòng nhập lại!!!!");
            }

        }
    }

    private static void addProduct() {
        try {
            Scanner scan1 = new Scanner(System.in);
            Scanner scan2 = new Scanner(System.in);
            Scanner scan3 = new Scanner(System.in);

            System.out.println("Mời bạn nhập ID");
            String id = scan1.nextLine();
            System.out.println("Mời bạn nhập tên");
            String name = scan2.nextLine();
            System.out.println("Mời bạn nhập giá");
            double price = scan1.nextDouble();
            System.out.println("Mời bạn nhập số lượng");
            int quantity = scan2.nextInt();
            System.out.println("Mời bạn nhập mô tả");
            String description = scan3.nextLine();
            Product product = new Product(id, name, price, quantity, description);
            productManager.addProducts(product);
        } catch (InputMismatchException e) {
            System.err.println("Sai kiểu dữ liệu");
        }

    }

    private static void editProduct() {
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        try {
            System.out.println("Mời bạn nhập ID");
            String id = scan1.nextLine();
            int check = productManager.checkID(id);
            if (check == -1) {
                System.out.println("Không có sản phẩm theo ID");
            } else {
                System.out.println("Mời bạn nhập ID mới");
                String newID = scan1.nextLine();
                System.out.println("Mời bạn nhập tên mới");
                String newName = scan2.nextLine();
                System.out.println("Mời bạn nhập giá mới");
                double newPrice = scan1.nextDouble();
                System.out.println("Mời bạn nhập số lượng mới");
                int newQuantity = scan2.nextInt();
                System.out.println("Mời bạn nhập mô tả mới");
                String newDes = scan1.nextLine();
                Product product = new Product(newID, newName, newPrice, newQuantity, newDes);
                productManager.editProduct(check, product);
            }
        } catch (InputMismatchException e) {
            System.out.println("Nhập sai dữ liệu");
        }
    }

    private static void removeProduct() {
        Scanner scan1 = new Scanner(System.in);
        try {
            System.out.print("Mời bạn nhập ID: ");
            String id = scan1.nextLine();
            int check = productManager.checkID(id);
            if (check == -1) {
                System.out.println("Không có sản phẩm theo ID");
                System.out.println("----------------------------------");
            } else {
                productManager.deleteProduct(check);
            }
        } catch (InputMismatchException e) {
            System.out.println("Nhập sai dữ liệu");
        }
    }
}
