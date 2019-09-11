$.ajaxSetup({ cache: false });

var zapros = false;

var kolichestvoZaprosovZagruzkiMuzikalnixAlbomov = 0;

var mygallery = new fadeSlideShow({
	wrapperid: "fotki",
	dimensions: [247, 171],
	imagearray: [
		["img/lolik_03_01.jpg", "", "", ""],
		["img/lolik_03_02.jpg", "", "", ""],
		["img/lolik_03_03.jpg", "", "", ""]
	],
	displaymode: {type:'auto', pause:5000, cycles:0, wraparound:false},
	persist: false,
	fadeduration: 1000,
	descreveal: "ondemand",
	togglerid: ""
})

$(window).load(function () {
	$('#zagruzochnaiaRamka').hide();
	$('#zaglavnaiaRamka').show();
	zagruzitGlavnuiuRamku();
});

function smenitYazik(yazik) {
	$("#yazik").val(yazik);
	$("#yazikForm").submit();
}

function zagruzitGlavnuiuRamku() {
	if ($("#adresPerenapravlenia").val() == '') {
		otkritStranicu(1); //Glavnaia
	} else {
		if (zapros == false) {
			zapros = true;
			$("#glavnaiaRamka").fadeOut(500, function() {
				$("#glavnaiaRamka").hide();
				$.get($("#adresPerenapravlenia").val(), $("#formaPerenapravlenia").serializeArray(), function(data) {
					$("#glavnaiaRamka").html(data);
					$("#glavnaiaRamka").show(1500, function(){ zapros = false; });
					$("#scrollbar1").tinyscrollbar();
				});
			});
		}
	}
}

function otkritStranicu(tipRazdela) {
	if (zapros == false) {
		zapros = true;
		otkritOpros(tipRazdela);
		$("#glavnaiaRamka").fadeOut(500, function() {
			$("#glavnaiaRamka").hide();
			$.get("otkritStranicu.json", {tipRazdelaKod: tipRazdela}, function(data) {
				$("#glavnaiaRamka").html(data);
				$("#glavnaiaRamka").show(1500, function(){ zapros = false; });
				$("#scrollbar1").tinyscrollbar();
			});
		});
	}
}

function otkritOpros(tipRazdela) {
	if (tipRazdela == '1') { //Glavnaia
		if ($("#oprosRamka").html() != null) {
			$("#oprosRamka").fadeOut(400, function() {
				$("#oprosRamka").remove();
				$.get("otkritOpros.json", function(data) {
					$("#glavnaiaRamka").before(data);
					$("#oprosRamka").hide();
					$("#oprosRamka").fadeIn(1500);
				});	
			});
		} else {
			$.get("otkritOpros.json", function(data) {
				$("#glavnaiaRamka").before(data);
				$("#oprosRamka").hide();
				$("#oprosRamka").delay(400);
				$("#oprosRamka").fadeIn(1500);
			});	
		}
	} else {
		$("#oprosRamka").fadeOut(400, function() {
			$("#oprosRamka").remove();
		});
	}
}

function otkritStranicuFotografii(albom) {
	if (zapros == false) {
		zapros = true;
		$("#glavnaiaRamka").fadeOut(500, function() {
			$.get("otkritStranicuFotografii.json", {albomId: albom}, function(data) {
				$("#glavnaiaRamka").html(data);
				$("#glavnaiaRamka").show(1500, function(){ zapros = false; });
				$("#scrollbar1").tinyscrollbar();
			});
		});
	}
}

function otkritStranicuMuzikalnogoAlboma(muzikalniiAlbom) {
	if (zapros == false) {
		zapros = true;
		$("#glavnaiaRamka").fadeOut(500, function() {
			$.get("otkritStranicuMuzikalniiAlbom.json", {muzikalniiAlbomId: muzikalniiAlbom}, function(data) {
				$("#glavnaiaRamka").html(data);
				$("#glavnaiaRamka").show(1500, function(){ zapros = false; });
				$("#scrollbar1").tinyscrollbar();
			});
		});
	}
}

function otvetitNaOpros() {
	$.post($("#polzovatelOtvet").attr("action"), $("#polzovatelOtvet").serializeArray(), function(data) {
		$("#oprosRamka").remove();
		$("#glavnaiaRamka").before(data);
		$("#oprosKonteiner").slideToggle(0);
	});
}

function pokazatOpros() {
	$("#oprosKonteiner").slideToggle("slow");
}

function kupitMuzikalniiAlbom(muzikalniiAlbomId) {
	if (zapros == false) {
		$("#soobshenia").fadeOut(500, function() { 
			$("#zagruzkaSoobshenia").fadeIn(1000, function() { 
				$.getJSON("kupitMuzikalniiAlbom.json", {muzikalniiAlbomId: muzikalniiAlbomId}, function(data) {
					zapros = false;
					if (data.prodolzhit) {
						window.location = data.url;
					} else {
						$("#zagruzkaSoobshenia").fadeOut(500, function() {
							$("#soobsheniaTekst").html(data.soobshenieOshibki);
							$("#soobshenia").fadeIn(1000, function(){ 
								$("#scrollbar1").tinyscrollbar();
							});
						});
					}
				});
			});
		});
	}
}

function podtverditPokupkuMuzikalnogoAlboma(muzikalniiAlbomId) {
	if (zapros == false) {
		zapros = true;
		$("#zagruzkaSoobshenia").fadeIn(1000, function() { 
			$.get("podtverditPokupkuMuzikalnogoAlboma.json", {muzikalniiAlbomId: muzikalniiAlbomId}, function(data) {
				zapros = false;
				$("#glavnaiaRamka").fadeOut(500, function() {
					$("#glavnaiaRamka").html(data);
					$("#glavnaiaRamka").show(1500, function(){
						$("#scrollbar1").tinyscrollbar();
					});
				});		
			});
		});
	}
}

function inicializaciaMuzikalniiAlbomInstrukcia() {
	$("#scrollbar1").tinyscrollbar();
	$("#ssilkaOtkritZagruzkuMuzikalnogoAlbomaHTML").click(function(e) {
		e.preventDefault();
		otkritStranicuZagruzkiMuzikalnogoAlboma();
	});
}

function otkritStranicuZagruzkiMuzikalnogoAlboma() {
	if (zapros == false) {
		zapros = true;
		$("#glavnaiaRamka").fadeOut(500, function() {
			$.get("otkritZagruzkuMuzikalnogoAlboma.json", {pokupatelEmail: $("#pokupatelEmail").val()}, function(data) {
				$("#glavnaiaRamka").html(data);
				$("#glavnaiaRamka").show(1500, function(){ zapros = false; });
				$("#scrollbar1").tinyscrollbar();
			});
		});
	}
}

function naitiMuzikalnieAlbomiKuplenniePolzovatelem() {
	if (zapros == false && $('#pokupatelEmail').val() != '') {
		$("#soobshenia").fadeOut(400, function() {
			$("#soobsheniePolzovatelNeNaiden").hide();
			$("#soobshenieZagruzokNeOstalos").hide();
			if (kolichestvoZaprosovZagruzkiMuzikalnixAlbomov < 3) {
				$("#zagruzkaMuzikalnogoAlbomaTable").fadeOut(500, function() {
					zapros = true;
					$.get("naitiMuzikalnieAlbomiPolzovatela.json", {pokupatelEmail: $('#pokupatelEmail').val()}, function(data) {
						zapros = false;
						kolichestvoZaprosovZagruzkiMuzikalnixAlbomov++;
						if (data.muzikalniiAlbomiTranzakciiKolichestvo > 0) {
							$('#zagruzkaMuzikalnogoAlbomaTable tbody').html('');
							$.each(data.muzikalniiAlbomiTranzakcii, function(i, item){
								$('#zagruzkaMuzikalnogoAlbomaTable tbody').append(
									'<tr height="60">' +
										'<td valign="top" class="kompoziciaTekst">' +
											'<input type="radio" class="radio" id="muzikalniiAlbomTranzakcia.id.' + item.muzikalniiAlbomTranzakciaId + '" name="muzikalniiAlbomTranzakcia.id" value="' + item.muzikalniiAlbomTranzakciaId + '"/>' + 
											'<label for="muzikalniiAlbomTranzakcia.id.' + item.muzikalniiAlbomTranzakciaId + '">' + item.muzikalniiAlbomTranzakciaMuzikalniiAlbomNazvanie + '</label>' + 
										'</td>' +
										'<td valign="top" class="kompoziciaTekst">' + item.muzikalniiAlbomTranzakciaMuzikalniiAlbomIntegranti + '</td>' +
										'<td valign="top" class="kompoziciaTekst">' + item.muzikalniiAlbomTranzakciaChislo + '</td>' +
										'<td valign="top" class="kompoziciaTekst" align="center" id="kolichestvoOstavshixsiaZagruzok' + item.muzikalniiAlbomTranzakciaId + '">' + item.muzikalniiAlbomTranzakciaKolichestvoOstavshixsiaZagruzok + '</td>' +
									'</tr>'
								);
							});
							$('#zagruzkaMuzikalnogoAlbomaTable').fadeIn(1500);
						} else {
							$("#soobsheniePolzovatelNeNaiden").show();
							$("#soobshenia").fadeIn(1000);
						}
						$("#scrollbar1").tinyscrollbar();
					});
				});
			} else {
				$("#soobsheniePolzovatelNeNaiden").show();
				$("#soobshenia").fadeIn(1000);
			}
		});
	}
}

function inicializaciaZagruzkiMuzikalnogoAlboma() {
	$("#knopkaZagruzitMuzikalniiAlbom").click(function(e) {
		e.preventDefault();
		$("#soobshenia").fadeOut(400, function() {
			$("#soobsheniePolzovatelNeNaiden").hide();
			$("#soobshenieZagruzokNeOstalos").hide();
			
			var muzikalniiAlbomTranzakciaId = $('input[name=muzikalniiAlbomTranzakcia.id]:checked', '#zagruzitMurikalniiAlbomForm').val();
			if (muzikalniiAlbomTranzakciaId != null) {
				var kolichestvoOstavshixsiaZagruzok = $("#kolichestvoOstavshixsiaZagruzok" + muzikalniiAlbomTranzakciaId);
				if (kolichestvoOstavshixsiaZagruzok.html() > 0) {
					kolichestvoOstavshixsiaZagruzok.html(kolichestvoOstavshixsiaZagruzok.html() - 1);
				    window.location.href = 'zagruzitMurikalniiAlbom.json?muzikalniiAlbomTranzakcia.id='+muzikalniiAlbomTranzakciaId;
				} else {
					$("#soobshenieZagruzokNeOstalos").show();
					$("#soobshenia").fadeIn(1000);
					$("#scrollbar1").tinyscrollbar();
				}
			}
		});
	});
}

function nazhatFlag(kartinka, yazik) {
	kartinka.src = "img/lolik_" + yazik + "_n.jpg";
}

function otzhatFlag(kartinka, yazik) {
	kartinka.src = "img/lolik_" + yazik + ".jpg";
}

function nazhatKnopku(kartinka, nomer) {
	kartinka.src = "img/" + yazik + "/lolik_" + nomer + "_n.jpg";
}

function otzhatKnopku(kartinka, nomer) {
	kartinka.src = "img/" + yazik + "/lolik_" + nomer + ".jpg";
}

function nazhatOpros(kartinka) {
	kartinka.src = "img/" + yazik + "/lolik-opros_n.png";
}

function otzhatOpros(kartinka) {
	kartinka.src = "img/" + yazik + "/lolik-opros.png";
}

function nazhatPngKnopku(kartinka) {
	kartinka.src = kartinka.src.substring(0, kartinka.src.lastIndexOf('.')) + "_n.png";
}
function otzhatPngKnopku(kartinka) {
	kartinka.src = kartinka.src.substring(0, kartinka.src.lastIndexOf('_n')) + ".png";
}

function nazhatBolshuiuRamku(tablica) {
	$("#" + tablica + " #verxnaiaBolshaiaRamka").attr("background", "img/lolik-bolshaia-ramka_v_n.png");
	$("#" + tablica + " #levaiaBolshaiaRamka").attr("background", 	"img/lolik-bolshaia-ramka_l_n.png");
	$("#" + tablica + " #pravaiaBolshaiaRamka").attr("background",	"img/lolik-bolshaia-ramka_p_n.png");
	$("#" + tablica + " #nizhnaiaBolshaiaRamka").attr("background", "img/lolik-bolshaia-ramka_n_n.png");
}

function otzhatBolshuiuRamku(tablica) {
	$("#" + tablica + " #verxnaiaBolshaiaRamka").attr("background", "img/lolik-bolshaia-ramka_v.png");
	$("#" + tablica + " #levaiaBolshaiaRamka").attr("background", 	"img/lolik-bolshaia-ramka_l.png");
	$("#" + tablica + " #pravaiaBolshaiaRamka").attr("background",	"img/lolik-bolshaia-ramka_p.png");
	$("#" + tablica + " #nizhnaiaBolshaiaRamka").attr("background", "img/lolik-bolshaia-ramka_n.png");
}

function nazhatSrednuiuRamku(tablica) {
	$("#" + tablica + " #verxnaiaSrednaiaRamka").attr("background", "img/lolik-srednaia-ramka_v_n.png");
	$("#" + tablica + " #levaiaSrednaiaRamka").attr("background", 	"img/lolik-srednaia-ramka_l_n.png");
	$("#" + tablica + " #pravaiaSrednaiaRamka").attr("background",	"img/lolik-srednaia-ramka_p_n.png");
	$("#" + tablica + " #nizhnaiaSrednaiaRamka").attr("background", "img/lolik-srednaia-ramka_n_n.png");
}

function otzhatSrednuiuRamku(tablica) {
	$("#" + tablica + " #verxnaiaSrednaiaRamka").attr("background", "img/lolik-srednaia-ramka_v.png");
	$("#" + tablica + " #levaiaSrednaiaRamka").attr("background", 	"img/lolik-srednaia-ramka_l.png");
	$("#" + tablica + " #pravaiaSrednaiaRamka").attr("background",	"img/lolik-srednaia-ramka_p.png");
	$("#" + tablica + " #nizhnaiaSrednaiaRamka").attr("background", "img/lolik-srednaia-ramka_n.png");
}

function nazhatMalenkuiuRamku(tablica) {
	$("#" + tablica + " #verxnaiaMalenkaiaRamka").attr("background", "img/lolik-malenkaia-ramka_v_n.png");
	$("#" + tablica + " #levaiaMalenkaiaRamka").attr("background", 	"img/lolik-malenkaia-ramka_l_n.png");
	$("#" + tablica + " #pravaiaMalenkaiaRamka").attr("background",	"img/lolik-malenkaia-ramka_p_n.png");
	$("#" + tablica + " #nizhnaiaMalenkaiaRamka").attr("background", "img/lolik-malenkaia-ramka_n_n.png");
}

function otzhatMalenkuiuRamku(tablica) {
	$("#" + tablica + " #verxnaiaMalenkaiaRamka").attr("background", "img/lolik-malenkaia-ramka_v.png");
	$("#" + tablica + " #levaiaMalenkaiaRamka").attr("background", 	"img/lolik-malenkaia-ramka_l.png");
	$("#" + tablica + " #pravaiaMalenkaiaRamka").attr("background",	"img/lolik-malenkaia-ramka_p.png");
	$("#" + tablica + " #nizhnaiaMalenkaiaRamka").attr("background", "img/lolik-malenkaia-ramka_n.png");
}

function nazhatKnopkuPaypal(kartinka) {
	kartinka.src = kartinka.src.substring(0, kartinka.src.indexOf('.')) + "_n" + kartinka.src.substring(kartinka.src.indexOf('.'), kartinka.src.length);
}

function otzhatKnopkuPaypal(kartinka) {
	kartinka.src = kartinka.src.substring(0, kartinka.src.indexOf('_n')) + kartinka.src.substring(kartinka.src.indexOf('_n') + 2, kartinka.src.length);
}

function nazhatZvezdu(nomer, div, zvezdaObiektId) {
	if ($("#" + div + zvezdaObiektId + " #ocenenoPolzovatelem" + zvezdaObiektId).val() == 'false') {
		$("#" + div + zvezdaObiektId + " img").each(function(indeks) {
			if (indeks < nomer) {
				$(this).attr('src', 'img/lolik-zvezda_v.png');
			} else {
				$(this).attr('src', 'img/lolik-zvezda.png');	
			}
		});
	}
}

function obnovitZvezdiObiekta(div, zvezdaObiektId) {
	var srednaiaOcenka = "#" + div + zvezdaObiektId + " #srednaiaOcenka" + zvezdaObiektId;
	var zvezdaDivImg   = "#" + div + zvezdaObiektId + " img";
	obnovitZvezdi(srednaiaOcenka, zvezdaDivImg);
}

function obnovitZvezdi(srednaiaOcenkaObiekta, zvezdiDivImg) {
	var absolutnaiaDesatichnaiaRaznica = vichislitAbsolutnuiuDesatichnuiuRaznicu($(srednaiaOcenkaObiekta).val());
	var celaiaChastSredneiOcenkiRazdela = $(srednaiaOcenkaObiekta).val() - absolutnaiaDesatichnaiaRaznica;
	$(zvezdiDivImg).each(function(indeks) {
		if (indeks + 1 <= celaiaChastSredneiOcenkiRazdela || indeks <= celaiaChastSredneiOcenkiRazdela && absolutnaiaDesatichnaiaRaznica >= 0.75) {
			$(this).attr('src', 'img/lolik-zvezda_n.png');
		} else if (indeks <= celaiaChastSredneiOcenkiRazdela && absolutnaiaDesatichnaiaRaznica >= 0.25 && absolutnaiaDesatichnaiaRaznica < 0.75) {
			$(this).attr('src', 'img/lolik-polu-zvezda.png');
		} else {
			$(this).attr('src', 'img/lolik-zvezda.png');
		}
	});
}

function vichislitAbsolutnuiuDesatichnuiuRaznicu(srednaiaOcenkaObiekta) {
	var okruglonnaiaSrednaiaOcenkaObiekta = parseFloat(srednaiaOcenkaObiekta).toFixed(0);
	if (srednaiaOcenkaObiekta > okruglonnaiaSrednaiaOcenkaObiekta) {
		return absolutnaiaDesatichnaiaRaznica = srednaiaOcenkaObiekta - okruglonnaiaSrednaiaOcenkaObiekta;
	} else {
		return absolutnaiaDesatichnaiaRaznica = 1 - (okruglonnaiaSrednaiaOcenkaObiekta - srednaiaOcenkaObiekta);
	}
}

function otmetitZvezdoiObiekt(url, div, zvezdaObiektId, znachenieKod) {
	if ($("#" + div + zvezdaObiektId + " #ocenenoPolzovatelem" + zvezdaObiektId).val() == 'false') {
		$.get(url, {obiektId: zvezdaObiektId, znachenie: znachenieKod}, function(data) {
			$("#" + div + zvezdaObiektId + " #srednaiaOcenka" + zvezdaObiektId).val(data.srednaiaOcenkaObiekta);
			obnovitZvezdiObiekta(div, zvezdaObiektId);
			$("#" + div + zvezdaObiektId + " #ocenenoPolzovatelem" + zvezdaObiektId).val(true);
		});
	}
}