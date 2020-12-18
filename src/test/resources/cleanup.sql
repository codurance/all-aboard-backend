DELETE FROM resources;
DELETE FROM subtopics;
DELETE FROM catalogue_topic;
DELETE FROM topics;
DELETE FROM catalogue;
alter sequence hibernate_sequence restart with 1;
