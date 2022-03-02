const options = {
	navigation: {
		nextEl: '.swiper-button-next',
		prevEl: '.swiper-button-prev'
	},
	autoHeight: true,
	slidesPerView: 4,
	spaceBetween: 30,
	slidesPerGroup: 4
}

new Swiper('.journal__tiles', options);