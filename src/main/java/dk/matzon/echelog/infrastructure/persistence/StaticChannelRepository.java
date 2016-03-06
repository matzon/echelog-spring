package dk.matzon.echelog.infrastructure.persistence;

import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.domain.model.ChannelRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
public class StaticChannelRepository implements ChannelRepository {

    private Map<String, List<Channel>> store;

    public StaticChannelRepository() {
        store = new HashMap<>();

        // freenode
        ArrayList<Channel> freenodeChannels = new ArrayList<>();
        freenodeChannels.add(new Channel("freenode", "#angularjs", "Official AngularJS IRC channel", "http://angularjs.org/", false));
        freenodeChannels.add(new Channel("freenode", "#jasig-uportal", "Official uPortal IRC channel", "http://www.jasig.org/uportal", false));
        store.put("freenode", freenodeChannels);

        // codehaus
        ArrayList<Channel> codehause = new ArrayList<>();
        freenodeChannels.add(new Channel("codehaus", "#apache-ode", "Official Apache ODE (Orchestration Director Engine) channel", "http://ode.apache.org/", true));
    }

    public List<Channel> findAll(boolean includeArchived) {
        List<Channel> result = new ArrayList<>();
        for (List<Channel> channels : store.values()) {
            result.addAll(channels);
        }
        return result;
    }

    public List<Channel> findByNetwork(String _network) {
        List<Channel> result = new ArrayList<>();
        if (store.containsKey(_network)) {
            result.addAll(store.get(_network));
        }
        return result;
    }

    public Channel findByNetworkAndName(String _network, String _name) {
        Channel result = null;
        if (store.containsKey(_network)) {
            List<Channel> channels = store.get(_network);
            for (Channel channel : channels) {
                if (_name.equals(channel.getName())) {
                    return channel;
                }
            }

        }
        return result;
    }
}