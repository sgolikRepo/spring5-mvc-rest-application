package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        generateCategories();

        generateCustomers();

        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor1");
        vendor1.setVendorUrl("http:/vendor1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor2");
        vendor2.setVendorUrl("http:/vendor2");
        vendorRepository.save(vendor2);

        Vendor vendor3 = new Vendor();
        vendor3.setName("Vendor3");
        vendor3.setVendorUrl("http:/vendor3");
        vendorRepository.save(vendor3);
    }

    private void generateCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories loaded : " + categoryRepository.count());
    }

    private void generateCustomers() {
        Customer customer1 = new Customer();
        customer1.setCustomerUrl("https://111");
        customer1.setFirstName("John");
        customer1.setLastName("Smith");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerUrl("https://222");
        customer2.setFirstName("John2");
        customer2.setLastName("Smith2");
        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setCustomerUrl("https://333");
        customer3.setFirstName("John3");
        customer3.setLastName("Smith3");
        customerRepository.save(customer3);
    }
}
