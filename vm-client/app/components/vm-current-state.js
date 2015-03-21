import Ember from 'ember';
import layout from '../templates/components/vm-current-state';

export default Ember.Component.extend({
  layout: layout,
  currentLayers: null,
  didInsertElement() {
    var self = this;
    var element = Ember.$('.map-wrapper')[0];
    var map = new Datamap({
      element: element,
      scope: 'world',
      height: 600,
      geographyConfig: {
        popupOnHover: false,
        highlightOnHover: false
      },
      setProjection: function(element) {
        var projection = d3.geo.equirectangular()
          .center([23, 45])
          .rotate([10, -5.3])
          .scale(7000)
          .translate([element.offsetWidth / 2, element.offsetHeight / 2]);
        var path = d3.geo.path()
          .projection(projection);

        return {
          path: path,
          projection: projection
        };
      },
      fills: {
        defaultFill: 'rgb(211, 211, 211)',
        NODE_COLOR: 'red',
        OK: 'green',
        WARN: 'orange',
        NOK: 'red'
      },

    });

    this.getNodes().then(function(response) {
      var radiusScale = d3.scale.linear();
      var maxUserCount = parseInt(response._embedded.nodes.sortBy('active_users')[0].active_users)
      radiusScale.range([2, 10]).domain([0, maxUserCount]);

      function computeNodeHealth(layers) {

        const totalMaxRobustness = layers.mapBy('max_robustness').reduce(function(previousValue, currentValue, index, array) {
          return parseInt(previousValue) + parseInt(currentValue);
        });
        const totalCurrentRobustness = layers.mapBy('current_robustness').reduce(function(previousValue, currentValue, index, array) {
          return parseInt(previousValue) + parseInt(currentValue);
        });

        return (totalCurrentRobustness / totalMaxRobustness * 100);

      }
      var r = response._embedded.nodes.map(n => {
        var health = computeNodeHealth(n._embedded.layers);
        var radius = radiusScale(parseInt(n.active_users));
        return {
          fillKey: self.getNodeColor(health),
          radius: radius,
          health: health,
          _metadata: n,
          name: n.venue_name,
          latitude: n.venue_lat,
          longitude: n.venue_long
        };
      });

      map.bubbles(r, {
        popupTemplate: function(geo, data) {
          self.set('currentNodeLayers', data._metadata._embedded.layers.sortBy('level'));
          return '<div class="hoverinfo"><b>Health ' + (data.health).toFixed(2) +
          '%</b> | Active users: ' + data._metadata.active_users + '<br>' +
            'Address: ' + data.name + ' - ' + data._metadata.venue_address + '<br>' +
            'IP address:' + data._metadata.ip_address;
        }
      });
    }, function reject(reason) {
      console.error(reason);
    });
  },

  getNodeColor(health) {
    if(health < 50) {
      return 'NOK';
    } else if(health < 99) {
      return 'WARN';
    } else {
      return 'OK';
    }
  },
  getNodes() {
    return new Ember.RSVP.Promise(function(resolve, reject) {
      Ember.$.ajax({
        url: 'http://localhost:8080/vm-backend/read/nodes',
        dataType: 'json',
        success: resolve,
        error: function(jqXHR) {
          reject(jqXHR);
        }
      });
    });
  }
});
