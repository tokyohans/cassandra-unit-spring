CREATE TABLE ci_tracker (
  userid text,
  comments map<text, frozen<map<text, int>>>,
  PRIMARY KEY(userid)
);