import Ember from 'ember';
import layout from '../templates/components/vm-current-state';

export default Ember.Component.extend({
  layout: layout,
  didInsertElement() {
    var element = this.get('element');
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
          .rotate([10, -4])
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
        defaultFill: '#ABDDA4',
        ONE: 'blue',
        TWO: 'red'
      },

    });

    this.getNodes().then(function (response) {
      debugger;
      map.bubbles([{
        name: 'Castle Bravo',
        radius: 25,
        yeild: 15000,
        country: 'USA',
        significance: 'First dry fusion fuel "staged" thermonuclear weapon; a serious nuclear fallout accident occurred',
        fillKey: 'USA',
        date: '1954-03-01',
        latitude: 11.415,
        longitude: 165.1619
      }, {
        name: 'Tsar Bomba',
        radius: 20,
        yeild: 50000,
        country: 'USSR',
        fillKey: 'RUS',
        significance: 'Largest thermonuclear weapon ever tested—scaled down from its initial 100 Mt design by 50%',
        date: '1961-10-31',
        latitude: 49.0524622,
        longitude: 13.7744861
      }]);
    }, function reject(reason) {

    });
  },

  getNodes() {
    return new Ember.RSVP.Promise(function (resolve, reject) {
      Ember.$.ajax({
        url:'http://localhost:8080/vm-backend/read/nodes',
         dataType: 'json',
         success: resolve,
         error: function (jqXHR) {
             reject(jqXHR);
          }
      });
    });
  }
});
