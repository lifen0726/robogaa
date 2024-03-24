async function initMap() {
  const map = new google.maps.Map(document.getElementById("map"), {
      zoom: 8,
      center: { lat: 23.2339, lng: 120.9637 },
  });

  // Other initialization code...

  const infoWindow = new google.maps.InfoWindow({
    content: "",
    disableAutoPan: true,
  });

  const labels = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  const response = await fetch('../../js/trail/trailsCoordinateAndDetail.json');
  const locations = await response.json();

  let markerDetailsMap = new Map(); // This makes it accessible in all functions


  const markersJson = locations.map((location, i) => {
    try {
      // Make sure the place_coordinate exists and is valid
      if (!location.place_coordinate || typeof location.place_coordinate.lat !== 'number' || typeof location.place_coordinate.lng !== 'number') {
        throw new Error('Invalid location data');
      }

      const label = labels[i % labels.length];
      const marker = new google.maps.Marker({
        position: location.place_coordinate,
        // label,  
      });



   // Associate marker with details
   markerDetailsMap.set(marker, locations[i]); // Assuming locations is the array of details corresponding to each marker





      //點擊marker顯示資訊
      marker.addListener("click",async () => {
        console.log("location:")
        console.log(location.place_name)

        //API call to fetch trails
        let url = "http://localhost:25565/dto/getTrailByTname.controller/"+location.place_name;
        // let url = "http://localhost:25565/dto/getTrailByTname.controller/abc";
        try {
          const response = await axios.get(url); // Wait for the HTTP request to complete
          // const trails = response.data; // Assign the response data to trails
          
          // Log the trails object to console for debugging
          // console.log(response.data[0].name)
          // console.log(response.data[0].id)
          // console.log("trails:");
          // console.log(trails);
      
          const contentString = '<div><h3><a href="/toDetailPage?id='+ response.data[0].id +'">' + response.data[0].name + '</a></h3><p>' + location.place_coordinate.lat + '</p></div>';
          infoWindow.setContent(contentString);


          // return trails; // Return the trails object
        } catch (error) {
          // Log any errors
          console.log("error", error);
      
          // Depending on your error handling strategy, you might want to rethrow the error or return a default value
          throw error; // Rethrow the error or return an appropriate value
        }


        // const contentString = '<div><h3>' + location.place_name + '</h3><p>' + location.place_coordinate.lat + '</p></div>';
        // const contentString = '<div><h3><a href="http://localhost:25565/trail.controller">' + location.place_name + '</a></h3><p>' + location.place_coordinate.lat + '</p></div>';
        


        // fetch('http://localhost:25565/123/1')
        // .then(response => {
        //   if (!response.ok) {
        //     throw new Error('Network response was not ok');
        //   }
        //   return response.json(); // 解析 JSON 数据
        // })
        // .then(data => {
        //   // 处理从服务器返回的数据
        //   console.log(data);
        // })
        // .catch(error => {
        //   console.error('There has been a problem with your fetch operation:', error);
        // });
      


        
        // infoWindow.setContent(contentString);

        // infoWindow.setContent(label);
        infoWindow.open(map, marker);
      });





      return marker;
    } catch (error) {
      console.error('Error creating marker for location:', location, error);
      return null; // Return null for markersJson that couldn't be created
    }
  }).filter(marker => marker !== null); // Filter out the null markersJson

  new MarkerClusterer(map, markersJson, { imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m' });









  //當地圖畫面改變，畫面所涵蓋的座標
  // Here comes the new part
  const input = document.getElementById("place-input");
  const searchBox = new google.maps.places.SearchBox(input);
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

  map.addListener("bounds_changed", () => {
      searchBox.setBounds(map.getBounds());
  });

  let markers = []; // Re-declare markers array here if it's not already declared at a higher scope
  let markerDetails = {}; // details of markers within the current map bounds
                          // Assuming this object holds details for each marker, keyed by marker ID or some unique identifier



  // Function to retrieve details of markers within the current map bounds
  function getMarkerDetailsInBounds() {
    const bounds = map.getBounds();
    let detailsWithinBounds = [];
  
    markerDetailsMap.forEach((details, marker) => {
      if (bounds.contains(marker.getPosition())) {
        detailsWithinBounds.push(details);
      }
    });
  
    // console.log("Details of markers within bounds:", detailsWithinBounds);
    return detailsWithinBounds;
  }
  











  //搜尋列
  //update sidebar
  searchBox.addListener("places_changed", () => {
    const places = searchBox.getPlaces();
    // console.log(places)
    if (places.length === 0) {
      return;
    }
  
    // Clear out the old markers.
    markers.forEach((marker) => marker.setMap(null));
    markers = [];
    const bounds = new google.maps.LatLngBounds();
    places.forEach((place) => {
      if (!place.geometry || !place.geometry.location) {
        console.log("Returned place contains no geometry");
        return;
      }
      // Here, instead of directly working with markers,
      // you could call a function to fetch trail data near this location and update the sidebar
      console.log("places:")
      console.log(place.formatted_address)
      updateSidebarWithPlaceInfo(place);
  
      if (place.geometry.viewport) {
        // Only geocodes have viewport.
        bounds.union(place.geometry.viewport);
      } else {
        bounds.extend(place.geometry.location);
      }
    });
    map.fitBounds(bounds);


    // After updating the map's bounds:
    getMarkerDetailsInBounds(); // This will log the details of markers within the new bounds
  });
  
  //搜尋後，路徑畫面更新
  // Function to update the sidebar with place information
  function updateSidebarWithPlaceInfo(place) {
    // Fetch trail data related to the selected place
    // For demonstration, let's assume we have a function `fetchTrailData`
    fetchTrailData(place.formatted_address).then(trailData => {
      console.log(trailData)
      
      // Assuming `trailData` is an array of trail objects
      // Clear existing sidebar content
      const sidebar = document.querySelector('.sidebar');
      // sidebar.innerHTML = ''; // Clear current content
  
      // Add new trail data to the sidebar
      trailData.forEach(trail => {
        console.log(trail)
        const trailElement = document.createElement('div');
        trailElement.className = 'location-item';
        trailElement.innerHTML = `<h3>${trail.name}</h3><p>${trail.description}</p>`;
        // sidebar.appendChild(trailElement);
      });
    }).catch(error => {
      console.error('Error updating sidebar:', error);
    });
  }
  
  // Example fetchTrailData function
  async function fetchTrailData(location) {

    // console.log("location:")
    // console.log(location)
    //過濾'台灣'，以便資料庫查詢
    let originalString = location;
    let modifiedString = originalString.includes('台灣') ? originalString.replace('台灣', '') : originalString;
    // console.log(modifiedString); // Output: 台北市


    // Your API call to fetch trails based on location
    let url = "http://localhost:25565/trailDto.controller";


    try {
      const response = await axios.get(url); // Wait for the HTTP request to complete
      const trails = response.data; // Assign the response data to trails
      
      // Log the trails object to console for debugging
      // console.log("trails:");
      // console.log(trails);
  
      return trails; // Return the trails object
    } catch (error) {
      // Log any errors
      console.log("error", error);
  
      // Depending on your error handling strategy, you might want to rethrow the error or return a default value
      throw error; // Rethrow the error or return an appropriate value
    }
  }
  
  
  
  // Rest of your marker and clustering logic...
}

window.initMap = initMap;
