INSERT INTO category (category_name) VALUES
  ("Mobile applications"),
  ("Investment projects"),
  ("Business (shares) for sale");

INSERT INTO startup (startup_name, current_investment, needed_investment, description, rating, category, status) VALUES
  ("Pixpie", 500, 30000,
   "Pixpie provides reduced traffic usage (webP, bpg, smart bandwidth optimization), content adaptation for every device, app server offload and image manipulation (face detection, resizing). We speed up media content loading in mobile applications up to 4 times.",
   30, 1, "Approved"),
  ("Business service automation platform", 25000, 250000,
   "full-stack solution on the platform that automates a business service for example: -for Your Taxi Service; -for Your Pizza Shop; Booking, Order, Registration, Cabinet, Admin panel & Delivery serviceMobile apps (Android & iOS), Admin panel & Website (HTML5 UP)",
   70, 2, "Ready for approve"),
  ("Service for effective advertising campaigns", 2000, 100000,
   "Marketers and brands spend a lot of time  searching influencers, agreeing the price,  the brief and the task of a campaign,  making pre-payment,  post-payment  and building analytics of such a campaign. And there are always risks: influencers could take the money and disappear or the post wouldn’t be posted on time, the same risks influencers have on brands.",
   NULL, 3, "Draft");

INSERT INTO user (first_name, last_name, phone_number, email, country, city, login, password, is_active) VALUES
  ("Admin", "Admin", "000-00-00", "admin@gmail.com", "USA", "Chicago", "admin",
   "$2a$11$s/XA/33mxJuvORUi/kskfeMcu5h3fUB9OsLIcwXoVfswIt.pvx1EW", 1),
  ("Maria", "Anders", "111-22-33", "Maria.anders@gmail.com", "Ukraine", "Kyiv", "manders", "$2a$11$zSeb0Euf0ZhFgcTnBFeDGusufrRzDZD/8VshSj6/b13.cckNvyd9.", 1),
  ("Antonio", "Moreno", "222-33-44", "Antonio.moreno@gmail.com", "Italy", "Turin", "amoreno", "$2a$11$zSeb0Euf0ZhFgcTnBFeDGusufrRzDZD/8VshSj6/b13.cckNvyd9.", 1),
  ("Thomas", "Hardy", "333-44-55", "Thomas.Hardy@gmail.com", "Greece", "Sparta", "thardy", "$2a$11$zSeb0Euf0ZhFgcTnBFeDGusufrRzDZD/8VshSj6/b13.cckNvyd9.", 1),
  ("Hanna", "Moos", "444-55-66", "Hanna.moos@gmail.com", "Hungary", "Budapest", "hmoos", "$2a$11$zSeb0Euf0ZhFgcTnBFeDGusufrRzDZD/8VshSj6/b13.cckNvyd9.", 1),
  ("Martin", "Sommer", "555-66-77", "Martin.sommer@gmail.com", "Germany", "Berlin", "msommer", "$2a$11$zSeb0Euf0ZhFgcTnBFeDGusufrRzDZD/8VshSj6/b13.cckNvyd9.", 1);

INSERT INTO startup_evaluation (user_id, startup_id, mark) VALUES
  (3, 1, 1),
  (4, 1, 2),
  (5, 1, 5),
  (6, 1, 4),
  (2, 2, 10),
  (4, 2, 4),
  (5, 2, 9),
  (6, 2, 5);

INSERT INTO user_startup (user_id, startup_id) VALUES
  (2, 1),
  (3, 2),
  (4, 3);

INSERT INTO permission (permission_name) VALUES
  ("Create startup"),
  ("Update startup"),
  ("Delete startup"),
  ("Reject startup"),
  ("Approve startup"),
  ("Close startup"),
  ("Invest"),
  ("Access to 'Draft' startups"),
  ("Access to 'Deleted' startups"),
  ("Access to 'Rejected' startups"),
  ("Access to 'Ready for Approve' startups"),
  ("Access to 'Approved' startups"),
  ("Access to 'Closed' startups"),
  ("Block user"),
  ("Edit user");

INSERT INTO role (role_name) VALUES
  ("ROLE_ADMIN"),
  ("ROLE_INVESTOR"),
  ("ROLE_ANONYMOUS"),
  ("ROLE_FOUNDER");

INSERT INTO user_role (user_id, role_id) VALUES
  (1, 1),
  (2, 3),
  (3, 3),
  (4, 3),
  (5, 2),
  (6, 2);

INSERT INTO role_permission (role_id, permission_id) VALUES
  (1, 3),
  (1, 4),
  (1, 5),
  (1, 6),
  (1, 8),
  (1, 9),
  (1, 10),
  (1, 11),
  (1, 12),
  (1, 13),
  (1, 14),
  (1, 15),
  (2, 7),
  (2, 12),
  (3, 1),
  (3, 2),
  (3, 3),
  (3, 6),
  (3, 7),
  (3, 8),
  (3, 10),
  (3, 11),
  (3, 12);