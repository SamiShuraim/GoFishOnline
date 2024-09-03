package Objects;

import java.net.InetAddress;
import java.util.HashMap;

public class PlayerObject {
    private String name;
    private InetAddress address;

    public PlayerObject(String name, InetAddress address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public InetAddress getAddress() {
        return address;
    }

    public HashMap<String, String> toMap() {
        HashMap<String, String> res = new HashMap<>();
        res.put("name", name);
        res.put("address", address.getHostAddress());
        return res;
    }
}
