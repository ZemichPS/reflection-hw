import simpleJson.impl.typedes.Tokenizer;

public class Main {
    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.tokenize(getSourceLight());
        String[] zalupa = (String[]) tokenizer.getWorkMap().get("permissions");
        System.out.println(zalupa);
    }

    static String getSource() {
        return """
                {
                   "customer_id": "c56a4180-65aa-42ec-a945-5fd21dec0538",
                   "customer_name": "Valeriy Hvasko",
                   "customer_address": "123 Main Street",
                   "age": 32,
                   "permissions": [
                     "create_report_scope",
                     "read_report_scope",
                     "edit_report_scope"
                   ],
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

    static String getSourceLight() {
        return """
                {
                  "customer_id": "c56a4180-65aa-42ec-a945-5fd21dec0538",
                  "customer_name": "Valeriy Hvasko",
                  "customer_address": "123 Main Street",
                  "age": 32,
                  "address": {
                    "city": "Ostrovec",
                    "region": "Grono",
                    "zip": 231201
                  },
                  "permissions": [
                    "create_report_scope",
                    "read_report_scope",
                    "edit_report_scope"
                  ]
                }
                """;
    }
}
