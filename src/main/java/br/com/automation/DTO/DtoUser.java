package br.com.automation.DTO;

public class DtoUser {

    private String _firstName;
    private String _lastName;
    private String _zipCode;

    public DtoUser(String firstName, String lastName, String zipCode)
    {
        this._firstName = firstName;
        this._lastName = lastName;
        this._zipCode = zipCode;
    }

    public String getFirstName()
    {
        return _firstName;
    }
    public void setFirstName(String firstName)
    {
        this._firstName = firstName;
    }

    public String getLastName()
    {
        return _lastName;
    }
    public void setLastName(String lastName)
    {
        this._lastName = lastName;
    }

    public String getZipCode()
    {
        return _zipCode;
    }
    public void setZipCode(String zipCode)
    {
        this._zipCode = zipCode;
    }


}
