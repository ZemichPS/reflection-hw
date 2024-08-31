import model.Person;
import model.Product;
import model.ProductDto;
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

        final ProductDto product = jsonDeserializer.deserialize(testSimpleSourceWithInheritance(), ProductDto.class);
        System.out.println(product);
    }

    public static String testSimpleSourceWithInheritance() {
        return """
                {
                   "uuid": "123e4567-e89b-12d3-a456-426614174000",
                   "name": "Smartphone",
                   "description": "Latest model smartphone with advanced features",
                   "price$": 799.99,
                   "sale": true,
                   "supplier": {
                     "id": 98765,
                     "name": "Tech Supplier Inc.",
                     "description": "Leading supplier of electronic devices"
                   },
                   "inBoxGadget": [
                     {
                       "id": 1,
                       "name": "Charger",
                       "description": "Fast charging adapter"
                     },
                     {
                       "id": 2,
                       "name": "Earphones",
                       "description": "High-quality in-ear headphones"
                     },
                     {
                       "id": 3,
                       "name": "USB Cable",
                       "description": "USB Type-C charging cable"
                     }
                   ],
                   "availableFunctions": [
                     "Bluetooth",
                     "WiFi",
                     "GPS",
                     "NFC",
                     "Face Recognition"
                   ]
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
