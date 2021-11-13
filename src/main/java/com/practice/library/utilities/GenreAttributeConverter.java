package com.practice.library.utilities;

import com.practice.library.entities.Genre;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// https://thorben-janssen.com/hibernate-enum-mappings/

//@Converter(autoApply=true)
public class GenreAttributeConverter implements AttributeConverter<Genre, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Genre attribute) {
        if (attribute == null)
            return null;

        switch (attribute) {
            case NONFICTION:
                return 1;

            case FICTION:
                return 2;

            case DRAMA:
                return 3;

            case POETRY:
                return 4;

            case FANTASY:
                return 5;

            case SCIENCE_FICTION:
                return 6;

            case HORROR:
                return 7;

            case MYSTERY:
                return 8;

            case HISTORICAL_FICTION:
                return 9;

            case REALISTIC_FICTION:
                return 10;

            case FOLKLORE:
                return 11;

            case MYTHOLOGY:
                return 12;

            case AUTOBIOGRAPHY:
                return 13;

            case BIOGRAPHY:
                return 14;

            case ACTION:
                return 15;

            case ADVENTURE:
                return 16;

            case COMEDY:
                return 17;

            case ROMANCE:
                return 18;

            case ACADEMIC:
                return 19;

            case SCIENTIFIC:
                return 20;

            default:
                throw new IllegalArgumentException(attribute + " not supported.");
        }
    }

    @Override
    public Genre convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case 1:
                return Genre.NONFICTION;

            case 2:
                return Genre.FICTION;

            case 3:
                return Genre.DRAMA;

            case 4:
                return Genre.POETRY;

            case 5:
                return Genre.FANTASY;

            case 6:
                return Genre.SCIENCE_FICTION;

            case 7:
                return Genre.HORROR;

            case 8:
                return Genre.MYSTERY;

            case 9:
                return Genre.HISTORICAL_FICTION;

            case 10:
                return Genre.REALISTIC_FICTION;

            case 11:
                return Genre.FOLKLORE;

            case 12:
                return Genre.MYTHOLOGY;

            case 13:
                return Genre.AUTOBIOGRAPHY;

            case 14:
                return Genre.BIOGRAPHY;

            case 15:
                return Genre.ACTION;

            case 16:
                return Genre.ADVENTURE;

            case 17:
                return Genre.COMEDY;

            case 18:
                return Genre.ROMANCE;

            case 19:
                return Genre.ACADEMIC;

            case 20:
                return Genre.SCIENTIFIC;

            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }
}
