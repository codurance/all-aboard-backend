INSERT INTO catalogue (lp_id, lp_name, lp_description) VALUES (1, 'first learning path title', 'first learning path description');
INSERT INTO catalogue (lp_id, lp_name, lp_description) VALUES (2, 'second learning path title', 'second learning path description');
INSERT INTO topics (t_id, t_name, t_description) VALUES (1, 'first topic name', 'first topic description');
INSERT INTO topics (t_id, t_name, t_description) VALUES (2, 'second topic name', 'second topic description');
INSERT INTO catalogue_topic (t_id, lp_id) VALUES (1,1);
INSERT INTO catalogue_topic (t_id, lp_id) VALUES (2,1);