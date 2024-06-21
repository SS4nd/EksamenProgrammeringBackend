-- Table for participants (deltager)
CREATE TABLE IF NOT EXISTS deltager (
                                        DeltagerID INT AUTO_INCREMENT PRIMARY KEY,
                                        Navn VARCHAR(255) NOT NULL,
    Gender VARCHAR(10),
    Alder INT,
    Klub VARCHAR(255)
    );

-- Table for disciplines
CREATE TABLE IF NOT EXISTS disciplin (
                                         DisciplinID INT AUTO_INCREMENT PRIMARY KEY,
                                         Navn VARCHAR(255) NOT NULL,
    Resultattype ENUM('cm', 'sek') NOT NULL
    );

-- Junction table for linking participants to disciplines
CREATE TABLE IF NOT EXISTS deltager_disciplin (
                                                  DeltagerID INT NOT NULL,
                                                  DisciplinID INT NOT NULL,
                                                  PRIMARY KEY (DeltagerID, DisciplinID),
    FOREIGN KEY (DeltagerID) REFERENCES deltager(DeltagerID),
    FOREIGN KEY (DisciplinID) REFERENCES disciplin(DisciplinID)
    );

-- Table for results
CREATE TABLE IF NOT EXISTS resultat (
                                        ResultatID INT AUTO_INCREMENT PRIMARY KEY,
                                        DeltagerID INT NOT NULL,
                                        DisciplinID INT NOT NULL,
                                        Dato DATE,
                                        Resultatværdi VARCHAR(255),
    FOREIGN KEY (DeltagerID) REFERENCES deltager(DeltagerID),
    FOREIGN KEY (DisciplinID) REFERENCES disciplin(DisciplinID)
    );
