package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("=== TEST 1: Seller findById ===");
		Seller seller = sellerDao.findById(3);

		System.out.println(seller);
		System.out.println("\n=== TEST 2: Seller findByDep ===");
		List<Seller> list = new ArrayList<>();
		Department dep = new Department(2, null);
		list = sellerDao.findByDepartment(dep);
		for(Seller sel : list) {
			System.out.println(sel);
		}
		System.out.println("\n=== TEST 3: Seller findAll ===");
		List<Seller> list1 = new ArrayList<>();
		list1 = sellerDao.findAll();
		for(Seller sel : list1) {
			System.out.println(sel);
		}
//		System.out.println("\n=== TEST 4: Seller insert ===");
//	//	Seller newSeller = new Seller(null, "greg", "greg@gmail.com", new Date(), 4000.0, dep);
//	//	sellerDao.insert(newSeller);
//	//	System.out.println(newSeller.getId());
//		System.out.println("\n=== TEST 5: Seller update ===");
//		seller = sellerDao.findById(1);
//		seller.setName("Martha Eayne");
//		sellerDao.update(seller);
//		System.out.println("update complete");
		System.out.println("\n=== TEST 6: Seller delete ===");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		sc.close();

	}

}
