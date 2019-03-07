--STATEMENT createPoster
INSERT INTO POSTER (
  PosterID,
  PosterTitle,
  AuthorStatus,
  AuthorPin
)
VALUES (
  :PosterId,
  :PosterTitle,
  :PosterStatus,
  :AuthorPin
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
  PosterAbstract = :PosterAbstract,
  PosterDepartment = :PosterDepartment,
  PosterLink = :PosterLink,
  PosterFileName = :PosterFileName,
  PosterPresentationLink = :PosterPresentationLink,
  AuthorFirstName = :AuthorFirstName,
  AuthorLastName = :AuthorLastName,
  AuthorEmail = :AuthorEmail,
  AuthorPin = :AuthorPin,
  AuthorStatus = :AuthorStatus
WHERE
  PosterID = :PosterID;


