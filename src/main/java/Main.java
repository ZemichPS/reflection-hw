import model.Person;
import model.Product;
import simpleJson.api.JsonDeserializer;
import simpleJson.impl.JsonDeserializerImpl;
import simpleJson.impl.JsonParserImpl;
import simpleJson.impl.typedes.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JsonDeserializer jsonDeserializer = new JsonDeserializerImpl(
                new JsonParserImpl(),
                List.of(
                        new BooleanDeserializer(),
                        new StringDeserializer(),
                        new IntegerDeserializer(),
                        new DoubleDeserializer(),
                        new UUIDDeserializer()
                )
        );

        final Product product = jsonDeserializer.deserialize(testSimpleSource(), Product.class);
        System.out.println(product);
    }

    public static String testSimpleSource() {
        return """
                  "uuid": "123e4567-e89b-12d3-a456-426614174000",
                  "name": "Laptop",
                  "description": "High performance laptop with 16GB RAM and 512GB SSD.",
                  "price": 999.99,
                  "sale": true
                """;
    }

    public static String testSource() {
        return """
                {
                  "id": "c0a801b3-16c4-42a8-b49a-122b93ff7a31",
                  "name": "John Doe",
                  "age": 35,
                  "address": {
                    "street": "123 Main St",
                    "city": "Springfield",
                    "country": "USA",
                    "buildingNumber": "4A",
                    "postalCode": 12345
                  },
                  "permissions": [
                    "READ_REPORT",
                    "WRITE_REPORT",
                    "EDIT_REPORT"
                  ]
                }
                """;
    }


    static String getHardSource() {
        return """
                {
                   "customer_id": "c56a4180-65aa-42ec-a945-5fd21dec0538",
                   "customer_name": "Valeriy Hvasko",
                   "customer_address": "123 Main Street",
                   "is_man": true,
                   "has_children": false,
                   "age": 32,
                   "permissions": [
                     "create_report_scope",
                     "read_report_scope",
                     "edit_report_scope"
                   ],
                    "address": {
                    "city": "Ostrovec",
                    "region": "Grono",
                    "zip": 231201
                  },
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

    static String getMiddleSource() {
        return """
                
                  "company": {
                    "name": "Tech Innovators Inc.",
                    "address": {
                      "street": "123 Innovation Way",
                      "city": "Silicon Valley",
                      "state": "CA",
                      "zipCode": "94043"
                    },
                    "departments": [
                      {
                        "id": 101,
                        "name": "Engineering",
                        "manager": {
                          "name": "Alice Johnson",
                          "email": "alice.johnson@techinnovators.com"
                        },
                        "employees": [
                          {
                            "id": 1,
                            "name": "John Doe",
                            "role": "Software Engineer",
                            "skills": ["Java", "Python", "Cloud Computing"]
                          },
                          {
                            "id": 2,
                            "name": "Jane Smith",
                            "role": "DevOps Engineer",
                            "skills": ["AWS", "Docker", "CI/CD"]
                          }
                        ]
                      },
                      {
                        "id": 102,
                        "name": "Marketing",
                        "manager": {
                          "name": "Bob Williams",
                          "email": "bob.williams@techinnovators.com"
                        },
                        "employees": [
                          {
                            "id": 3,
                            "name": "Emily Davis",
                            "role": "Marketing Specialist",
                            "skills": ["SEO", "Content Marketing", "Social Media"]
                          }
                        ]
                      }
                    ],
                    "projects": [
                      {
                        "id": 1001,
                        "name": "AI Research",
                        "departmentId": 101,
                        "budget": 500000,
                        "status": "In Progress"
                      },
                      {
                        "id": 1002,
                        "name": "New Website Launch",
                        "departmentId": 102,
                        "budget": 150000,
                        "status": "Completed"
                      }
                    ]
                  }
                }
                """;
    }

    static String getSuperHard() {
        return """
                {
                  "id": 1,
                  "name": "RootObject",
                  "metadata": {
                    "version": "1.0.0",
                    "author": "Your Name",
                    "creationDate": "2024-08-30"
                  },
                  "configurations": [
                    {
                      "id": 1001,
                      "type": "TypeA",
                      "settings": {
                        "enabled": true,
                        "threshold": 0.75,
                        "options": ["option1", "option2", "option3"],
                        "subSettings": {
                          "mode": "advanced",
                          "parameters": {
                            "param1": 100,
                            "param2": 200
                          }
                        }
                      }
                    },
                    {
                      "id": 1002,
                      "type": "TypeB",
                      "settings": {
                        "enabled": false,
                        "threshold": 0.85,
                        "options": ["option4", "option5"],
                        "subSettings": {
                          "mode": "basic",
                          "parameters": {
                            "param1": 300,
                            "param2": 400,
                            "param3": {
                              "subParam1": 500,
                              "subParam2": 600
                            }
                          }
                        }
                      }
                    }
                  ],
                  "users": [
                    {
                      "userId": 1,
                      "username": "user1",
                      "details": {
                        "email": "user1@example.com",
                        "roles": ["admin", "editor"],
                        "profile": {
                          "firstName": "John",
                          "lastName": "Doe",
                          "age": 30,
                          "addresses": [
                            {
                              "type": "home",
                              "street": "123 Main St",
                              "city": "Anytown",
                              "state": "CA",
                              "zip": "12345"
                            },
                            {
                              "type": "work",
                              "street": "456 Office Rd",
                              "city": "Businesstown",
                              "state": "NY",
                              "zip": "67890"
                            }
                          ]
                        }
                      }
                    },
                    {
                      "userId": 2,
                      "username": "user2",
                      "details": {
                        "email": "user2@example.com",
                        "roles": ["user"],
                        "profile": {
                          "firstName": "Jane",
                          "lastName": "Smith",
                          "age": 25,
                          "addresses": [
                            {
                              "type": "home",
                              "street": "789 Suburbia Blvd",
                              "city": "Villagetown",
                              "state": "TX",
                              "zip": "54321"
                            }
                          ]
                        }
                      }
                    }
                  ],
                  "logs": [
                    {
                      "logId": 1,
                      "timestamp": "2024-08-30T08:45:00Z",
                      "message": "System started",
                      "details": {
                        "severity": "INFO",
                        "context": {
                          "module": "startup",
                          "function": "initialize"
                        }
                      }
                    },
                    {
                      "logId": 2,
                      "timestamp": "2024-08-30T09:00:00Z",
                      "message": "User login",
                      "details": {
                        "severity": "WARN",
                        "context": {
                          "module": "authentication",
                          "function": "validateUser",
                          "user": {
                            "userId": 1,
                            "username": "user1"
                          }
                        }
                      }
                    }
                  ],
                  "projects": [
                    {
                      "projectId": 101,
                      "projectName": "Project Alpha",
                      "milestones": [
                        {
                          "milestoneId": 1,
                          "name": "Design",
                          "deadline": "2024-09-15",
                          "tasks": [
                            {
                              "taskId": 1,
                              "description": "Create wireframes",
                              "status": "completed",
                              "assignee": {
                                "userId": 1,
                                "username": "user1"
                              }
                            },
                            {
                              "taskId": 2,
                              "description": "Review designs",
                              "status": "pending",
                              "assignee": {
                                "userId": 2,
                                "username": "user2"
                              }
                            }
                          ]
                        },
                        {
                          "milestoneId": 2,
                          "name": "Development",
                          "deadline": "2024-11-01",
                          "tasks": [
                            {
                              "taskId": 3,
                              "description": "Set up environment",
                              "status": "in-progress",
                              "assignee": {
                                "userId": 1,
                                "username": "user1"
                              }
                            }
                          ]
                        }
                      ]
                    },
                    {
                      "projectId": 102,
                      "projectName": "Project Beta",
                      "milestones": [
                        {
                          "milestoneId": 3,
                          "name": "Planning",
                          "deadline": "2024-10-01",
                          "tasks": [
                            {
                              "taskId": 4,
                              "description": "Define scope",
                              "status": "pending",
                              "assignee": {
                                "userId": 2,
                                "username": "user2"
                              }
                            }
                          ]
                        }
                      ]
                    }
                  ],
                  "settings": {
                    "general": {
                      "theme": "dark",
                      "language": "en",
                      "notifications": {
                        "email": true,
                        "sms": false
                      }
                    },
                    "security": {
                      "passwordPolicy": {
                        "minLength": 8,
                        "requireNumbers": true,
                        "requireSpecialCharacters": true
                      },
                      "twoFactorAuthentication": {
                        "enabled": true,
                        "methods": ["sms", "email"]
                      }
                    }
                  }
                }
                """;
    }


}
