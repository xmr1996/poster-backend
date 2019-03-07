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
SELECT PosterID, AuthorStatus, PosterTitle, AuthorFirstName, AuthorLastName FROM POSTER;

--STATEMENT getPostersByStatus
SELECT PosterID, AuthorStatus, PosterTitle, AuthorFirstName, AuthorLastName FROM POSTER
WHERE AuthorStatus = :AuthorStatus;

--STATEMENT getAuthorPins
SELECT AuthorEmail, AuthorPin FROM POSTER;

--STATEMENT getAuthors
SELECT AuthorEmail, AuthorFirstName, AuthorLastName, AuthorStatus FROM POSTER;

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

--STATEMENT getScores


