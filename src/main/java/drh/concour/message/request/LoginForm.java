package drh.concour.message.request;

public class LoginForm {
//    @NotBlank
//    @Size(min=3, max = 60)
    private String Identifier;

//    @NotBlank
//    @Size(min = 6, max = 40)
    private String motDePasse;

    public String getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String identifier) {
        this.Identifier = identifier;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}