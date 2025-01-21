document.addEventListener("DOMContentLoaded", () => {
    // Carica i prodotti all'inizio
    fetchProducts();

    // Gestisce l'apertura della modale per aggiungere un nuovo prodotto
    const openAddProductModalButton = document.getElementById('open-add-product-modal');
    const newProductModal = document.getElementById('new-product-modal');
    
    openAddProductModalButton.addEventListener('click', () => {
        const modal = new bootstrap.Modal(newProductModal);
        modal.show();
    });

    // Gestisce la chiusura della modale
    const closeModalButton = document.querySelector('.btn-close');
    closeModalButton.addEventListener('click', () => {
        const modal = bootstrap.Modal.getInstance(newProductModal);
        modal.hide();
    });

    // Gestisce l'invio del modulo per aggiungere un nuovo prodotto
    const addProductForm = document.getElementById('add-product-form');
    addProductForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const newProduct = {
            nome: document.getElementById('nome').value,
            descrizione: document.getElementById('descrizione').value,
            categoria: document.getElementById('categoria').value,
            prezzo: parseFloat(document.getElementById('prezzo').value),
            rimanenza: parseInt(document.getElementById('rimanenza').value),
            abilitato: document.getElementById('abilitato').checked,
            visibile: document.getElementById('visibile').checked,
            scontoPrevendita: parseFloat(document.getElementById('sconto_prevendita').value)
        };

        let inizioPrevendita = (document.getElementById('edit-inizio_prevendita').value === "") ? null : document.getElementById('edit-inizio_prevendita').value;
        let dataUscita = (document.getElementById('edit-data_uscita').value === "") ? null : document.getElementById('edit-data_uscita').value;

        console.log(newProduct);
        
        const immagine = document.getElementById('immagine').files[0];

        const formData = new FormData();

        formData.append("prodotto", JSON.stringify(newProduct));
        formData.append("image", immagine)
        
        if (inizioPrevendita) {
            formData.append("prevendita", inizioPrevendita);
        } else {
            console.log("Nessuna data per inizio Prevendita");
        }
        if (dataUscita) {
            formData.append("dataUscita", dataUscita)
        } else {
            console.log("Nessuna data selezionata per data Uscita");
        }


        fetch('http://localhost:8080/api/prodotto/alt', {
            method: 'POST',
            body: formData
        })
        .then(function(response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Errore nell'inserimento del prodotto");
            }
        })
        .then(function(product) {
            alert("Prodotto aggiunto con successo!");
            fetchProducts(); // Ricarica la lista dei prodotti
            const modal = bootstrap.Modal.getInstance(newProductModal);
            modal.hide();
            console.log(product);
            location.reload();
        })
        .catch(function(error) {
            console.error("Errore fetch nell'inserimento del prodotto:", error);
        });
    });

    // Funzione per caricare i prodotti
    function fetchProducts() {
        const apiUrl = 'http://localhost:8080/api/prodotto';

        fetch(apiUrl)
        .then(function(response) {
            return response.json();
        })
        .then(function(products) {
            if (Array.isArray(products)) {
                displayProducts(products);
            } else {
                console.error("I prodotti non sono un array");
            }
        })
        .catch(function(error) {
            console.error("Errore durante il recupero dei dati:", error);
        });
    }

    // Funzione per visualizzare i prodotti
    function displayProducts(products) {
        const productsTableBody = document.getElementById("product-table-body");
        productsTableBody.innerHTML = "";

        products.forEach(function(product) {
            const productRow = createProductRow(product);
            productsTableBody.appendChild(productRow);
        });
    }

    // Funzione per creare una riga della tabella con un prodotto
    function createProductRow(product) {
        const row = document.createElement('tr');
        row.setAttribute('data-product-id', product.prodottoId);

        row.innerHTML = `
            <td><img src="${product.imgUrl}" alt="${product.nome}" width="50"></td>
            <td>${product.nome}</td>
            <td>${product.categoria}</td>
            <td>${product.prezzo}</td>
            <td>${product.rimanenza}</td>
            <td>
                <button class="btn btn-warning edit-product-btn">Modifica</button>
                <button class="btn btn-danger delete-product-btn">Elimina</button>
            </td>
        `;

        row.querySelector('.edit-product-btn').addEventListener('click', () => {
            openEditProductModal(product);
        });

        row.querySelector('.delete-product-btn').addEventListener('click', () => {
            deleteProduct(product.prodottoId, row);
        });

        return row;
    }

    function fillFormWithProductData(product) {
        // Precompila i campi del form nella modale di modifica
        document.getElementById('edit-nome').value = product.nome;
        document.getElementById('edit-descrizione').value = product.descrizione || "N/A";
        document.getElementById('edit-categoria').value = product.categoria || "N/A";
        document.getElementById('edit-prezzo').value = product.prezzo;
        document.getElementById('edit-rimanenza').value = product.rimanenza;
        document.getElementById('edit-abilitato').checked = product.abilitato;
        document.getElementById('edit-visibile').checked = product.visibile;
        document.getElementById('edit-inizio_prevendita').value = product.inizioPrevendita;
        document.getElementById('edit-data_uscita').value = product.dataUscita;
        document.getElementById('edit-sconto_prevendita').value = product.scontoPrevendita;

        // Gestione del campo immagine
        const editImmagineInput = document.getElementById('edit-immagine');
        const editImmaginePreview = document.getElementById('edit-immagine-preview');

        // // Mostriamo l'anteprima dell'immagine esistente, se presente
        // if (product.imgUrl && product.imgUrl !== "non-disponibile.jpg") {
        //     editImmaginePreview.src = product.imgUrl;
        //     editImmaginePreview.style.display = "block"; // Mostra l'anteprima
        // } else {
        //     // Se l'immagine è di default o non esiste, non mostriamo l'anteprima
        //     editImmaginePreview.style.display = "none";
        // }

        console.log(product.imgUrl);
        editImmaginePreview.src = product.imgUrl;

        editImmagineInput.addEventListener("change", (e) => {
            const file = e.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = e => {
                    editImmaginePreview.src = e.target.result
                }
                reader.readAsDataURL(file);
            } else {
                editImmaginePreview.src = "";
            }
        })
        
        

        // Impostiamo il tipo del campo immagine a "file" e lo lasciamo vuoto
        editImmagineInput.type = 'file';
        editImmagineInput.value = ''; // Rimuoviamo il valore, poiché non possiamo pre-compilare un campo file con un valore

        document.getElementById('edit-product-form').dataset.productId = product.prodottoId;
    }

    // Funzione per aprire la modale di modifica
    function openEditProductModal(product) {
        fillFormWithProductData(product);

        const editProductModal = document.getElementById('edit-product-modal');
        const modal = new bootstrap.Modal(editProductModal);
        modal.show();

        // Impostiamo il tasto di reset con i dati originali del prodotto
        const resetEditProductButton = document.getElementById('reset-edit-product-btn');
        resetEditProductButton.addEventListener('click', () => {
            resetForm(product); // Passa il prodotto corrente per ripristinare i dati originali
        });
    }

    // Funzione per eliminare un prodotto
    function deleteProduct(prodottoId, row) {
        if (confirm("Sei sicuro di voler eliminare questo prodotto?")) {
            const apiUrl = `http://localhost:8080/api/prodotto/${prodottoId}`;

            fetch(apiUrl, {
                method: 'DELETE'
            })
            .then(function(response) {
                if (response.ok) {
                    alert("Prodotto eliminato con successo!");
                    row.remove(); // Rimuovi la riga dalla tabella
                } else {
                    throw new Error("Errore durante l'eliminazione del prodotto");
                }
            })
            .catch(function(error) {
                console.error("Errore durante l'eliminazione del prodotto:", error);
            });
        }
    }

    // Gestisce l'invio del modulo per la modifica di un prodotto
    const editProductForm = document.getElementById('edit-product-form');
    editProductForm.addEventListener('submit', function(event) {
        event.preventDefault();

        // Estrai l'ID del prodotto dalla modale
        const prodottoId = editProductForm.dataset.productId;
        const editedProduct = {
            prodottoId: prodottoId, // Aggiungi l'ID del prodotto
            nome: document.getElementById('edit-nome').value,
            descrizione: document.getElementById('edit-descrizione').value,
            categoria: document.getElementById('edit-categoria').value,
            prezzo: parseFloat(document.getElementById('edit-prezzo').value),
            rimanenza: parseInt(document.getElementById('edit-rimanenza').value),
            // immagine: document.getElementById('edit-immagine').type === 'file' ? document.getElementById('edit-immagine').files[0] : null,
            abilitato: document.getElementById('edit-abilitato').checked,
            visibile: document.getElementById('edit-visibile').checked,
            scontoPrevendita: parseFloat(document.getElementById('edit-sconto_prevendita').value)
        };

        const editImmagine = document.getElementById('edit-immagine').type === 'file' ? document.getElementById('edit-immagine').files[0] : null;

        console.log(editImmagine);

        let inizioPrevendita = (document.getElementById('edit-inizio_prevendita').value === "") ? null : document.getElementById('edit-inizio_prevendita').value;
        let dataUscita = (document.getElementById('edit-data_uscita').value === "") ? null : document.getElementById('edit-data_uscita').value;


        const formData = new FormData();
        formData.append("prodotto", JSON.stringify(editedProduct));
        if (editImmagine) {
            formData.append("image", editImmagine); // Aggiungi immagine solo se presente
        } else {
            console.log("Nessuna immagine selezionata");
        }

        if (inizioPrevendita) {
            formData.append("prevendita", inizioPrevendita);
        } else {
            console.log("Nessuna data per inizio Prevendita");
        }
        if (dataUscita) {
            formData.append("dataUscita", dataUscita)
        } else {
            console.log("Nessuna data selezionata per data Uscita");
        }
        
        console.log("FormData:", formData); // Controlla i dati nel formData
        // formData.append("image", editImmagine);

        fetch(`http://localhost:8080/api/prodotto/alt`, {
            method: 'PUT',
            body: formData
        })
        .then(function(response) {
            if (response.ok) {
                alert("Prodotto modificato con successo!");
                fetchProducts(); // Ricarica la lista dei prodotti
                const modal = bootstrap.Modal.getInstance(document.getElementById('edit-product-modal'));
                modal.hide(); // Chiudi la modale
            } else {
                throw new Error("Errore nella modifica del prodotto");
            }
        })
        .catch(function(error) {
            console.error("Errore durante la modifica del prodotto:", error);
        });
    });

    function resetForm(product) {
        fillFormWithProductData(product); // Ripristina i dati originali nel modulo
    }

    // Gestisce l'anteprima dell'immagine
    document.getElementById('edit-immagine').addEventListener('change', function(event) {
        const file = event.target.files[0];
        const preview = document.getElementById('edit-immagine-preview');

        if (file) {
            const reader = new FileReader();

            reader.onload = function(e) {
                preview.src = e.target.result;
                preview.style.display = "block"; // Mostra l'anteprima dell'immagine
            };

            reader.readAsDataURL(file);
        }
    });
});
