import config.JdbcTemplateConfig;
import model.Address;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import repository.AddressRepositoryImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcTemplateConfig.class);

        AddressRepositoryImpl addressRepository = context.getBean(AddressRepositoryImpl.class);

        // Получаем все адреса
        List<Address> addresses = addressRepository.getAllAddresses();
        for (Address address : addresses) {
            System.out.println(address);
        }

        // Добавляем новый адрес
        Address newAddress = new Address(0, "Moscow", "Tverskaya", "10");
        addressRepository.addAddress(newAddress);

        // Получаем адрес по ID
        Address address = addressRepository.getAddressById(41);
        System.out.println("Address with ID 41: " + address);

        // Обновляем адрес
        address.setCity("Saint Petersburg");
        addressRepository.updateAddress(address);

        // Удаляем адрес
        addressRepository.deleteAddress(2);
    }
}
