(async() => {
    const res = await fetch('http://localhost:9000/api/v2-4/books');

    const result = await res.json();
    console.log(result);
})();