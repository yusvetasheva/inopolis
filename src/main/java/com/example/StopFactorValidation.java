package com.example;

public abstract class StopFactorValidation {

    // Шаблонный метод, который определяет общий алгоритм проверки стоп-фактора
    public final boolean validate(Customer customer) {
        // Общие шаги алгоритма, которые не меняются
        if (!checkEligibility(customer)) {
            return false;
        }

        // Шаги, которые могут быть разными для разных типов стоп-факторов
        return performSpecificCheck(customer);
    }

    // Общий шаг, например, проверка допустимости клиента (по каким-то базовым критериям)
    protected boolean checkEligibility(Customer customer) {
        return customer != null && customer.getAge() > 18; // Простой пример
    }

    // Абстрактный метод, который должен быть реализован в подклассах
    protected abstract boolean performSpecificCheck(Customer customer);
}

