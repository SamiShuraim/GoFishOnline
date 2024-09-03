package Objects;

import java.net.InetAddress;
import java.util.HashMap;

public record PlayerObject(String name, InetAddress address) {

    public HashMap<String, String> toMap() {
        HashMap<String, String> res = new HashMap<>();
        res.put("name", name);
        res.put("address", address.getHostAddress());
        return res;
    }
}
