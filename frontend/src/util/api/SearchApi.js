/**
 * Fetches search results.
 */
export async function search(query, signal) {
  console.log("Search | Fetching search results.");
  const uri = `/search?query=${query}`;
  console.log("URL IS " + process.env.REACT_APP_SEARCH_SERVICE_URL + uri);

  const data = await fetch(process.env.REACT_APP_SEARCH_SERVICE_URL + uri, {
    signal: signal,
    method:"GET"
  })
  .then( response => {
    console.log(response);
    if (!response.ok) {
      throw Error("Search | Failed to fetch search results: " + response.status);
    }
    console.log("Search | Response OK. Parsing...");
    return response.json();
  });
  return data;
}