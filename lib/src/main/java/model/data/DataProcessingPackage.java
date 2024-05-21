package model.data;

import model.data.readers.ExternalDataReader;
import model.data.validators.Validator;

public abstract class DataProcessingPackage<TRead, TValidated> {

    public final ExternalDataReader<TRead> reader;
    public final Validator<TValidated> validator;

    public DataProcessingPackage(ExternalDataReader<TRead> reader,
                                 Validator<TValidated> validator) {
        this.reader = reader;
        this.validator = validator;
    }

}
