import simpleJson.impl.typedes.Tokenizer;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        String source = """
                {
                  "customer_id": "c56a4180-65aa-42ec-a945-5fd21dec0538",
                  "customer_name": "Valeriy Hvasko",
                  "customer_address": "123 Main Street",
                  "product_list": [
                    {
                      "id": "d2b2e4a7-1c2c-4b9c-bd22-0d8415f5a571",
                      "name": "Laptop",
                      "price": 999.99,
                      "quantity": 1
                    },
                    {
                      "id": "a6a8b38c-58ec-49cb-944e-f0158b2a70a8",
                      "name": "Mouse",
                      "price": 49.99,
                      "quantity": 2
                    }
                  ]
                }
                """;


    }
}
