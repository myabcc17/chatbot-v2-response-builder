# 카카오 오픈빌더 스킬응답 JSON빌더

https://i.kakao.com/docs/skill-response-format#skillresponse

카카오 오픈빌더의 Json 형식 스킬응답을 보다 쉽게 만들수 있게 제공되는 SDK 입니다.

implementation("com.github.myabcc17:chatbot-response-v2-builder:0.0.14")

# Background

카카오 오픈빌더를 사용하면서 좀 더 정교한 응답을 위해 사용자가 스킬 서버를 직접 만들어야 할 때가 있다. 
사용자는 Json 응답을 만들게 되는데 매우 귀찮은 편이다. 이를 좀 더 편하게 만들 수 있게 SDK를 만들고 싶었다.
# Usage
```
com.github.myabcc17.SkillResponseV2Builder builder = new com.github.myabcc17.SkillResponseV2Builder();
builder.addComponent(Component); // max Component is 3
builder.addQuickReply(QuickReply); // max QuickReply is 10
builder.addContext(ContextValue);
builder.addData(String, Object);
...
...
builder.build() // return json string (SkillResponse)
```

## Components
자세한 스펙 사항은 [홈페이지 도움말](https://i.kakao.com/docs/skill-response-format#skillresponse)에도 나와있으니 참고하시면 됩니다..

폰트 효과:  **필수 파라미터**, *제약 사항*

* SimpleText
	* **text** (String)
* SimpleImage
	* **imageUrl** (String)
	* **altText** (String)
* BasicCard
	* title (String)
	* description (String) -  *max length is 230*
	* **thumbnail** (String)
	* profile (Profile) - ***not supported***
	* social (Social) - ***not supported***
	* button (List\<Button\>) - *max button is 3*
* ListCard
	* **header** (ListItem)
	* **items** (List\<ListItem\>) - *max item is 5*
	* buttons (List\<Button\>) - *max button is 2*
* CommerceCard
	* **description** (String) - *max length is 230*
	* **currency** (String)
	* **price** (int)
	* discount (int)
	* discountRate (int)
	* discountedPrice (int)
	* **buttons** (List\<Button\>) - *1 <= button.size <= 3*
	* **thumbnails** (List\<Thumbnail\>) - *only one element*
	* profile (Profile) - ***not supported***
* Carousel
	* type (String) - *one of ["basicCard", "commerceCard"]*
	* items (List\<CarouselItem\>) - *max item is 10*
	* carouselHeader (CarouselHeader)
## Common Component

* Button
	* **label** (String) - *max length is 14*
	* **action** (String) - *one of ["webLink", "message", "block", "phone", "share", "operator"]*
		* but, ***"operator" is not supported*** yet.
	* webLinkUrl (String) - *needed when action is "webLink" or "block"*
	* messageText (String) - *needed when action is "message"*
	* phoneNumber (String) - *needed when action is "phone"*
	* blockId (String) - *needed when action is "block"*
	* extra (Map<String, Object>)
* CarouselHeader
	* **title** (String)
	* **description** (String)
	* **thumbnail** (Thumbnail)
* Thumbnail
	* **imageUrl** (String)
	* link (Link)
	* fixedRatio (boolean)
	* width (int) - *needed when fixedRatio is true*
	* height (int) - *needed when fixedRatio is true*
* Link
	* pc (String)
	* mobile (String)
	* web (String)
* ListItem
	* **title** (String)
	* description (String)
	* imageUrl (String)
	* link (Link)

## Other Component

* QuickReply
	* **label** (String)
	* **action** (String) - *one of ["message", "block"]*
	* messageText (String) - *needed when action is message*
	* blockId (String) - *needed when action is block*
	* extra (Map<String, Object>)
* ContextValue
	* **name** (String)
	* **lifeSpan** (int)
	* params (Map<String, String>)

## Example
* BasicCard Example
	* `of` 로 생성하는 경우, 필수 인자가 반드시 포함되어야 한다.
	* `builder()`를 사용하여 생성하는 경우도 필수 인자가 반드시 포함되어야 한다.
		* 필수가 아닌 값들을 원하는 대로 넣은 후 `build()`메소드를 호출하여 Component를 생성한다.
```
Thumbnail thumbnail = Thumbnail.builder("http://sampleimage.com")
			.fixedRatio(true)
			.width(2)
			.height(1)
			.build();
BasicCard basicCard1 = BasicCard.of(thumbnail);    // BasicCard (only have thumbnail)
BasicCard basicCard2 = BasicCard.builder(thumbnail)
			.title("title")
			.description("sample okay?")
			.build();

builder.addComponent(basicCard1);
builder.addComponent(basicCard2);

System.out.println(builder.build()); // return json string
```
* Button Example
	* action 값에 따라 필수 필드가 달라지기 때문에 `builder()`를 사용하는 것을 권장
	* `QuickReply`, `Thumbnail` 도 마찬가지
```
Button button1 = Button.of("sample label", "phone", null, null, "010-1234-1234", null, null);
Button button2 = Button.builder("sample label", "phone")
			.phoneNumber("010-4567-4567")
			.build();
```

## Component Structure
[![](https://mermaid.ink/img/eyJjb2RlIjoiZ3JhcGggVERcblx0QVtTa2lsbFJlc3BvbnNlVjJdIC0tPiBCW1NraWxsVGVtcGxhdGVdXG5cdEEgLS0-IGNvbnRleHRDb250cm9sW0NvbnRleHRDb250cm9sXVxuXHRBIC0tPiBEW0RhdGFdXG5cdEIgLS0-IGNvbXBvbmVudHNbQ29tcG9uZW50c11cblx0QiAtLT4gcXVpY2tSZXBsaWVzW1F1aWNrUmVwbGllc11cblx0XG5cdHF1aWNrUmVwbGllcyAtLT4gcXJbUXVpY2tSZXBseV1cblxuXHRjb250ZXh0Q29udHJvbC0tPiBjdltDb250ZXh0VmFsdWVdXG5cdGNvbXBvbmVudHMgLS0-IHN0W1NpbXBsZVRleHRdXG5cdGNvbXBvbmVudHMgLS0-IHNpW1NpbXBsZUltYWdlXVxuXHRjb21wb25lbnRzIC0tPiBiY1tCYXNpY0NhcmRdXG5cdGNvbXBvbmVudHMgLS0-IGxjW0xpc3RDYXJkXVxuXHRjb21wb25lbnRzIC0tPiBjY1tDb21tZXJjZUNhcmRdXG5cdGNvbXBvbmVudHMgLS0-IGNhW0Nhcm91c2VsXVxuXHRjb21wb25lbnRzIC0tPiBjb21tb25bQ29tbW9uXVxuXHRcblx0Y29tbW9uIC0tPiBidXR0b25bQnV0dG9uXVxuXHRjb21tb24gLS0-IHRodW1ibmFpbFtUaHVtYm5haWxdXG5cdGNvbW1vbiAtLT4gbGlua1tMaW5rXVxuXHRjb21tb24gLS0-IGNhcm91c2VsSGVhZGVyW0Nhcm91c2VsSGVhZGVyXSIsIm1lcm1haWQiOnsidGhlbWUiOiJkZWZhdWx0In0sInVwZGF0ZUVkaXRvciI6ZmFsc2V9)](https://mermaid-js.github.io/mermaid-live-editor/#/edit/eyJjb2RlIjoiZ3JhcGggVERcblx0QVtTa2lsbFJlc3BvbnNlVjJdIC0tPiBCW1NraWxsVGVtcGxhdGVdXG5cdEEgLS0-IGNvbnRleHRDb250cm9sW0NvbnRleHRDb250cm9sXVxuXHRBIC0tPiBEW0RhdGFdXG5cdEIgLS0-IGNvbXBvbmVudHNbQ29tcG9uZW50c11cblx0QiAtLT4gcXVpY2tSZXBsaWVzW1F1aWNrUmVwbGllc11cblx0XG5cdHF1aWNrUmVwbGllcyAtLT4gcXJbUXVpY2tSZXBseV1cblxuXHRjb250ZXh0Q29udHJvbC0tPiBjdltDb250ZXh0VmFsdWVdXG5cdGNvbXBvbmVudHMgLS0-IHN0W1NpbXBsZVRleHRdXG5cdGNvbXBvbmVudHMgLS0-IHNpW1NpbXBsZUltYWdlXVxuXHRjb21wb25lbnRzIC0tPiBiY1tCYXNpY0NhcmRdXG5cdGNvbXBvbmVudHMgLS0-IGxjW0xpc3RDYXJkXVxuXHRjb21wb25lbnRzIC0tPiBjY1tDb21tZXJjZUNhcmRdXG5cdGNvbXBvbmVudHMgLS0-IGNhW0Nhcm91c2VsXVxuXHRjb21wb25lbnRzIC0tPiBjb21tb25bQ29tbW9uXVxuXHRcblx0Y29tbW9uIC0tPiBidXR0b25bQnV0dG9uXVxuXHRjb21tb24gLS0-IHRodW1ibmFpbFtUaHVtYm5haWxdXG5cdGNvbW1vbiAtLT4gbGlua1tMaW5rXVxuXHRjb21tb24gLS0-IGNhcm91c2VsSGVhZGVyW0Nhcm91c2VsSGVhZGVyXSIsIm1lcm1haWQiOnsidGhlbWUiOiJkZWZhdWx0In0sInVwZGF0ZUVkaXRvciI6ZmFsc2V9)

