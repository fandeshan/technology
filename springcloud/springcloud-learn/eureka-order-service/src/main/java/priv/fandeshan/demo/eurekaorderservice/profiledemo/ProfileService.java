package priv.fandeshan.demo.eurekaorderservice.profiledemo;

public class ProfileService {

    private String profile;

    public ProfileService(String profile){
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "ProfileService{" +
                "profile='" + profile + '\'' +
                '}';
    }
}
