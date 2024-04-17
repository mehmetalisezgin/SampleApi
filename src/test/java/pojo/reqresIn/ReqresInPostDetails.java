package pojo.reqresIn;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter and toString()
@NoArgsConstructor // without parameter
@AllArgsConstructor // with parameter
public class ReqresInPostDetails {
    private String name ;
    private String job;
}
