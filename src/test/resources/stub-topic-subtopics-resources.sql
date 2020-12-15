INSERT INTO topics (t_id, t_name, t_description) VALUES (2, 'second topic name', 'second topic description');
INSERT INTO subtopics (s_id, t_id, s_name) VALUES (3, 2, 'first subtopic of second topic name');
INSERT INTO resources (r_id, s_id, r_name, r_url) VALUES (1, 3, 'first resource of second topic of third subtopic', 'url 13');
