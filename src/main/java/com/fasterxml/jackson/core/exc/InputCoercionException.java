package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.*;

/**
 * Exception type for read-side problems that are not direct decoding ("parsing")
 * problems (those would be reported as basic
 * {@link StreamReadException}s),
 * but rather result from failed attempts to convert specific Java value out of valid
 * but incompatible input value. One example is numeric coercions where target number type's
 * range does not allow mapping of too large/too small input value.
 */
public class InputCoercionException extends StreamReadException
{
    private static final long serialVersionUID = 3L;

    /**
     * Input token that represents input value that failed to coerce.
     */
    protected final JsonToken _inputType;

    /**
     * Target type that input value failed to coerce to.
     */
    protected final Class<?> _targetType;
    
    /**
     * Constructor that uses current parsing location as location, and
     * sets processor (accessible via {@link #processor()}) to
     * specified parser.
     *
     * @param p Parser in use at the point where failure occurred
     * @param msg Exception mesage to use
     * @param inputType Shape of input that failed to coerce
     * @param targetType Target type of failed coercion
     */
    public InputCoercionException(JsonParser p, String msg,
            JsonToken inputType, Class<?> targetType) {
        super(p, msg);
        _inputType = inputType;
        _targetType = targetType;
    }

    /**
     * Fluent method that may be used to assign originating {@link JsonParser},
     * to be accessed using {@link #processor()}.
     *<p>
     * NOTE: `this` instance is modified and no new instance is constructed.
     */
    @Override
    public InputCoercionException withParser(JsonParser p) {
        _processor = p;
        return this;
    }

    /**
     * Accessor for getting information about input type (in form of token, giving "shape"
     * of input) for which coercion failed.
     *
     * @return "Shape" of input for which coercion failed, as {@link JsonToken}
     */
    public JsonToken getInputType() {
        return _inputType;
    }

    /**
     * Accessor for getting information about target type (in form of Java {@link java.lang.Class})
     * for which coercion failed.
     *
     * @return Target type of failed conversion
     */
    public Class<?> getTargetType() {
        return _targetType;
    }
}
