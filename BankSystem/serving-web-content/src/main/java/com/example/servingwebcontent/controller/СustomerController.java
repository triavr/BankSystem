package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.Entity.CustomerEntity;
import com.example.servingwebcontent.repository.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class СustomerController {

    @Autowired  // указываем спрингу, чтобы он внедрил обьект этого класса
    private CustomRepository customRepository;


    @PostMapping    //Создаем метод, который будет сохранять сущность в базу данных - метод для регистрации клиента
    public ResponseEntity registrationСustomer(@RequestBody CustomerEntity customer) {
        try {
            if (customRepository.findByCustomerName(customer.getCustomerName()) != null)    //перед сохранением пользователя делаем запрос на получение клиента с именем, которое пришло в теле запроса
            {
                return ResponseEntity.badRequest().body("Аккаунт с таким именем уже существует");
            }

                customRepository.save(customer);
            return ResponseEntity.ok("Вы успешно прошли регистрацию");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping()
    public ResponseEntity getCustomers() {     //Создаем метод, который будет вовращать ResponseEntity
        try {
            return ResponseEntity.ok("Сервер работает");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
