INSERT INTO public.project (id, title, description, technologies, github_url, start_year, end_year) VALUES
(random_uuid(), '{"en": "Kridava","fr": "Kridava","pt": "Kridava"}', '{"en": "A small 2D turn by turn game in Java made in 2017 as a college project","fr": "A small 2D turn by turn game in Java made in 2017 as a college project","pt": "A small 2D turn by turn game in Java made in 2017 as a college project"}', 'JAVA', 'https://github.com/Finrood/Kridava', '2017', '2017'),
(random_uuid(), '{"en": "Java-Trading-Bot","fr": "Java-Trading-Bot","pt": "Java-Trading-Bot"}', '{"en": "This Java project implements a cryptocurrency trading bot that fetches real-time prices from Binance and utilizes multiple Relative Strength Index (RSI) strategies to make trading decisions. The bot is designed to perform buy and sell actions based on predefined RSI thresholds and strategies. It also offers extensibility to include additional strategies like Moving Average Convergence Divergence (MACD) and the ability to run multiple strategies concurrently.","fr": "This Java project implements a cryptocurrency trading bot that fetches real-time prices from Binance and utilizes multiple Relative Strength Index (RSI) strategies to make trading decisions. The bot is designed to perform buy and sell actions based on predefined RSI thresholds and strategies. It also offers extensibility to include additional strategies like Moving Average Convergence Divergence (MACD) and the ability to run multiple strategies concurrently.","pt": "This Java project implements a cryptocurrency trading bot that fetches real-time prices from Binance and utilizes multiple Relative Strength Index (RSI) strategies to make trading decisions. The bot is designed to perform buy and sell actions based on predefined RSI thresholds and strategies. It also offers extensibility to include additional strategies like Moving Average Convergence Divergence (MACD) and the ability to run multiple strategies concurrently."}', 'JAVA,SPRING_BOOT,SQL', 'https://github.com/Finrood/Java-Trading-Bot', '2023', '2023'),
(random_uuid(), '{"en": "Piano-Music-Generator","fr": "Piano-Music-Generator","pt": "Piano-Music-Generator"}', '{"en": "This Python project implements a neural network that generates a short piano piece. The neural network is trained on a dataset of existing piano compositions to learn the patterns and structures present in music. It then generates a new piece that reflects the learned musical style.","fr": "This Python project implements a neural network that generates a short piano piece. The neural network is trained on a dataset of existing piano compositions to learn the patterns and structures present in music. It then generates a new piece that reflects the learned musical style.","pt": "This Python project implements a neural network that generates a short piano piece. The neural network is trained on a dataset of existing piano compositions to learn the patterns and structures present in music. It then generates a new piece that reflects the learned musical style."}', 'PYTHON,TENSORFLOW,AI,NEURAL_NETWORK', 'https://github.com/Finrood/Piano-Music-Generator', '2021', '2021'),
(random_uuid(), '{"en": "Flower-Image-Recognition","fr": "Flower-Image-Recognition","pt": "Flower-Image-Recognition"}', '{"en": "This Python project implements a flower image classifier using a neural network built with TensorFlow. The goal of this project is to classify images of different types of flowers into categories such as Gerbera, Iris, Lilac, Orchid, Daisy, Dandelion, Jonquil, Rose, Tulip, and Sunflower.","fr": "This Python project implements a flower image classifier using a neural network built with TensorFlow. The goal of this project is to classify images of different types of flowers into categories such as Gerbera, Iris, Lilac, Orchid, Daisy, Dandelion, Jonquil, Rose, Tulip, and Sunflower.","pt": "This Python project implements a flower image classifier using a neural network built with TensorFlow. The goal of this project is to classify images of different types of flowers into categories such as Gerbera, Iris, Lilac, Orchid, Daisy, Dandelion, Jonquil, Rose, Tulip, and Sunflower."}', 'PYTHON,TENSORFLOW,AI,NEURAL_NETWORK', 'https://github.com/Finrood/Flower-Image-Recognition', '2020', '2020'),
(random_uuid(), '{"en": "Traveling Salesman Problem Solver using Genetic Algorithm","fr": "Traveling Salesman Problem Solver using Genetic Algorithm","pt": "Traveling Salesman Problem Solver using Genetic Algorithm"}', '{"en": "This Java project implements a solution to the Traveling Salesman Problem (TSP) using a genetic algorithm. The Traveling Salesman Problem is a classic optimization problem where the goal is to find the shortest possible route that visits a set of cities and returns to the starting city.","fr": "This Java project implements a solution to the Traveling Salesman Problem (TSP) using a genetic algorithm. The Traveling Salesman Problem is a classic optimization problem where the goal is to find the shortest possible route that visits a set of cities and returns to the starting city.","pt": "This Java project implements a solution to the Traveling Salesman Problem (TSP) using a genetic algorithm. The Traveling Salesman Problem is a classic optimization problem where the goal is to find the shortest possible route that visits a set of cities and returns to the starting city."}' , 'JAVA,AI,GENETIC_ALGORITHM', 'https://github.com/Finrood/Travelling-salesman-Problem-Genetic-Algorithm', '2019', '2019');

INSERT INTO public.profile(id, lastname, firstname, birthdate, email, phone_number, biography, photo_key) VALUES
(random_uuid(), 'Petre', 'Samuel', '1994-12-21', 'petresamuel@gmail.com', '+32471431452', '{"en": "I''m Samuel Petre, a 28-year-old programmer with 13 years of coding experience. Passionate about tech and travel, I love exploring new technologies and cultures. When I''m not coding, you''ll find me on a Brazilian Jiu-Jitsu mat, learning the art of resilience and strategy. I''m all about building connections and collaborating. Let''s connect and create something amazing together!","fr": "I''m Samuel Petre, a 28-year-old programmer with 13 years of coding experience. Passionate about tech and travel, I love exploring new technologies and cultures. When I''m not coding, you''ll find me on a Brazilian Jiu-Jitsu mat, learning the art of resilience and strategy. I''m all about building connections and collaborating. Let''s connect and create something amazing together!","pt": "I''m Samuel Petre, a 28-year-old programmer with 13 years of coding experience. Passionate about tech and travel, I love exploring new technologies and cultures. When I''m not coding, you''ll find me on a Brazilian Jiu-Jitsu mat, learning the art of resilience and strategy. I''m all about building connections and collaborating. Let''s connect and create something amazing together!"}', null);
