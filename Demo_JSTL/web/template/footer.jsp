</div>
        <div id="footer">footer</div>
    </div>
    <script>
    function addTocart(id){
        $.ajax({
            url: "/Demo_JSTL/addToCart",
            type: "GET",
            data: {              
                pid : id
            },
            success: function (result) {
                var cartsize = document.getElementById("cart_header");
                cartsize.innerHTML = result;              
            },
            error: function (jqXHR, textStatus, errorThrown) {

            }

        });

    }

</script>
</body>
</html>