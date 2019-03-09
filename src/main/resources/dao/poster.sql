--STATEMENT createPoster
INSERT INTO POSTER (
  PosterID,
  PosterTitle,
  AuthorEmail,
  AuthorFirstName,
  AuthorLastName,
  AuthorStatus,
  AuthorPin,
  AuthorDepartment
)
VALUES (
  :PosterID,
  :PosterTitle,
  :AuthorEmail,
  :AuthorFistName,
  :AuthorLastName,
  :AuthorStatus,
  :AuthorPin,
  :AuthorDepartment
);

--STATEMENT getPosters
SELECT * FROM POSTER;

--STATEMENT getPostersByStatus
SELECT * FROM POSTER
WHERE AuthorStatus = :AuthorStatus;

--STATEMENT updatePoster
UPDATE POSTER SET
  PosterID = :PosterID,
  PosterTitle = :PosterTitle,
  AuthorEmail = :AuthorEmail,
  AuthorFirstName = :AuthorFirstName,
  AuthorLastName = :AuthorLastName,
  AuthorPin = :AuthorPin,
  AuthorStatus = :AuthorStatus,
  AuthorDepartment = :PosterDepartment
WHERE
  PosterID = :PosterID;


